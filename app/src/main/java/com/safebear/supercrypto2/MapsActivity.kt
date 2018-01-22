package com.safebear.supercrypto2

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null


    // This is when we start our app
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Check our permissions
        checkPermission()

    }

    // A variable to identify our permission request
    var ACCESSLOCATION=123

    // This is where we check our permissions
    fun checkPermission() {

        // If the SDK is higher than 23 we have have to request access to FINE_LOCATION as it's considered a dangerous permission
        if (Build.VERSION.SDK_INT >= 23) {

            // If we don't have this permission, then request it. This is also where we ID the request as ACCESSLOCATION
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),ACCESSLOCATION)
                return
            }
        }

        // Now we get the user location, assuming all location access has been granted.
        GetUserLocation()
    }

    // Displays a toast message before getting the location.
    fun GetUserLocation(){
        Toast.makeText(this,"User location access on",Toast.LENGTH_LONG).show()

        // Accessing our location listener class here.
        var myLocation=MyLocationListener()

        // Need a location manager to manage the location, this is a system service.
        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Run the myLocation class we initialised above every 3 milliseconds or every 3 metres
        // We've already checked for permission before calling this method, so we can ignore this error.
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)
        var mythread=myThread()
        mythread.start()



    }

    // This is where we implement the logic of what to do if our request is granted or denied by the user
    // We override the method and add our ACCESSLOCATION request code in there.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            ACCESSLOCATION->{

                if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    GetUserLocation()

                }else{
                    Toast.makeText(this,"We cannot access location, app will not work properly",Toast.LENGTH_LONG).show()
                }

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }


    // Empty location that will be populated by the MyLocationListener class, but accessable by all functions in this activity.
    var location:Location?=null

    // Get User Location class
    inner class MyLocationListener:LocationListener{

        // Constructor initializes the start location
        constructor(){
            location= Location("Start")
            location!!.longitude=0.0
            location!!.latitude=0.0
        }

        // Not changes location to p0, which I'm guessing is our current location.
        override fun onLocationChanged(p0: Location?) {
            location=p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            // Implemented when the GPS setting is switched on/off
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            // Implemented when GPS setting is switched on
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            // Implemented when GPS setting is switched off
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    // A place to store the old location so we can check if we've moved (see myThread class)
    var oldLocation:Location?=null

    // Gets the location from the 'location' variable and passes it to the UI
    inner class myThread:Thread{

        // I'm guessing this means we inherit from the parent outer class when we run this one.
        constructor():super(){
            oldLocation= Location("Start")
            oldLocation!!.longitude=0.0
            oldLocation!!.latitude=0.0
        }

        // override the run function
        override fun run() {
            while (true){
                try {

                    // if the location hasn't been updated, don't perform the code to add the marker and move camera to our location
                    // this allows us to move the map by scrolling with our finger without it snapping back to our location.
                    if(oldLocation!!.distanceTo(location)==0f){
                        continue
                    }
                    oldLocation=location

                    // Specify that you want to run this on the UI as threads can't communicate with the UI directly
                    // Add a marker in Sydney and move the camera
                    runOnUiThread {

                        // Need to clear the map everytime so you can start afresh with the markers
                        // mMap must be a global variable that is updated with onMapReady
                        mMap!!.clear()

                        // Show myself on the map
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("Me")
                                .snippet(" here is my location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.batman))
                            )

                        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f))

                        LoadBase()
                            // Show the bases on the map
                        for(i in 0..listBases.size-1){
                            var newBase=listBases[i]

                            if(newBase.IsCatch==false){
                                val baseLocation = LatLng(newBase.location!!.latitude, newBase.location!!.longitude)
                                mMap!!.addMarker(MarkerOptions()
                                        .position(baseLocation)
                                        .title(newBase.name!!)
                                        .snippet(newBase.des!! +", power:"+newBase.power!!)
                                        .icon(BitmapDescriptorFactory.fromResource(newBase.image!!)))

                                if (location!!.distanceTo(newBase.location)<2){
                                    newBase.IsCatch=true
                                    listBases[i]=newBase
                                    playerPower+=newBase.power!!
                                    Toast.makeText(applicationContext,"You catch a new base, your new power is "+playerPower, Toast.LENGTH_LONG).show()
                                }


                            }

                        }


                    }

                    // Just stops this running continuously and using all the CPU resources
                    Thread.sleep(1000)
                }catch (ex:Exception){

                }
            }
        }
    }

    // Set player power
    var playerPower=0.0

    // Get a list of bases available
    var listBases=ArrayList<Base>()

    fun LoadBase(){
        listBases.add(Base(R.drawable.base, "Base1", "This is base 1", 55.0, 37.779,-122.402))
        listBases.add(Base(R.drawable.base, "Base2", "This is base 2", 90.5, 37.795,-122.410))
        listBases.add(Base(R.drawable.base, "Base3", "This is base 3", 33.5, 37.782,-122.412))
    }


}
