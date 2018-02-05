package com.safebear.supercrypto2;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

/**
 * This is where we use the code on Ethereum to capture a base
 */

public class CaptureBase {

    public CaptureBase() throws IOException {

        // start up the instance of web3j library
        Web3j web3 = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/8rn4vemdFoq0VFYWBUPC"));
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
    }

    public void captureBase(String _password) throws IOException, CipherException {

        // set up contract instance
        // where will their wallet file be? How do we access it?
        Credentials credentials = WalletUtils.loadCredentials(_password,"/path/to/walletfile");


    }


}
