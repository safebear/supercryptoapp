package com.safebear.supercrypto2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.StaticArray10;
import org.web3j.abi.datatypes.generated.StaticArray2;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class BaseToken extends Contract {
    private static final String BINARY = "60606040526010600155600154600a0a600255336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610bbc806100616000396000f30060606040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634f64b2be146100725780638da5cb5b146101c9578063cc0b2c9f1461021e578063d68bac1314610281578063f2fde38b1461038c575b600080fd5b341561007d57600080fd5b61009360048080359060200190919050506103c5565b6040518080602001806020018681526020018581526020018481526020018381038352888181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156101325780601f1061010757610100808354040283529160200191610132565b820191906000526020600020905b81548152906001019060200180831161011557829003601f168201915b50508381038252878181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156101b55780601f1061018a576101008083540402835291602001916101b5565b820191906000526020600020905b81548152906001019060200180831161019857829003601f168201915b505097505050505050505060405180910390f35b34156101d457600080fd5b6101dc610408565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561022957600080fd5b61023f600480803590602001909190505061042d565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561028c57600080fd5b61038a600480803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091908060400190600280602002604051908101604052809291908260026020028082843782019150505050509190803590602001909190806101400190600a806020026040519081016040528092919082600a60200280828437820191505050505091908035906020019091905050610460565b005b341561039757600080fd5b6103c3600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610485565b005b6003818154811015156103d457fe5b9060005260206000209060110201600091509050806000019080600101908060020154908060050154908060100154905085565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60056020528060005260406000206000915054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600061046b856105da565b905061047c86888388888888610637565b50505050505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156104e057600080fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151561051c57600080fd5b8073ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a3806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000808260016002811015156105ec57fe5b602002015183600060028110151561060057fe5b6020020151016040518082815260200191505060405180910390206001900490506002548181151561062e57fe5b06915050919050565b600060016003805480600101828161064f9190610959565b9160005260206000209060110201600060e0604051908101604052808d81526020018c81526020018b81526020018a815260200189815260200188815260200187815250909190915060008201518160000190805190602001906106b492919061098b565b5060208201518160010190805190602001906106d192919061098b565b50604082015181600201556060820151816003019060026106f3929190610a0b565b506080820151816005015560a08201518160060190600a610715929190610a4b565b5060c0820151816010015550500390507f850c95362102f075c693790b1a1a3ae01725d54452a1b0850492e15b86b639768188888b8989898960405180898152602001806020018881526020018060200187600260200280838360005b8381101561078d578082015181840152602081019050610772565b5050505090500186815260200185600a60200280838360005b838110156107c15780820151818401526020810190506107a6565b5050505090500184815260200183810383528a818151815260200191508051906020019080838360005b838110156108065780820151818401526020810190506107eb565b50505050905090810190601f1680156108335780820380516001836020036101000a031916815260200191505b50838103825288818151815260200191508051906020019080838360005b8381101561086c578082015181840152602081019050610851565b50505050905090810190601f1680156108995780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a1336005600083815260200190815260200160002060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600660003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600081548092919060010191905055505050505050505050565b815481835581811511610986576011028160110283600052602060002091820191016109859190610a8b565b5b505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109cc57805160ff19168380011785556109fa565b828001600101855582156109fa579182015b828111156109f95782518255916020019190600101906109de565b5b509050610a079190610b02565b5090565b8260028101928215610a3a579160200282015b82811115610a39578251825591602001919060010190610a1e565b5b509050610a479190610b02565b5090565b82600a8101928215610a7a579160200282015b82811115610a79578251825591602001919060010190610a5e565b5b509050610a879190610b02565b5090565b610aff91905b80821115610afb5760008082016000610aaa9190610b27565b600182016000610aba9190610b27565b6002820160009055600382016000610ad29190610b6f565b6005820160009055600682016000610aea9190610b7d565b601082016000905550601101610a91565b5090565b90565b610b2491905b80821115610b20576000816000905550600101610b08565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610b4d5750610b6c565b601f016020900490600052602060002090810190610b6b9190610b02565b5b50565b506000815560010160009055565b5080600a0190610b8d9190610b02565b505600a165627a7a72305820a7101c7bc556548365c90d7ac2c2b6a8287e9826a36b26df94d8d9deca2127820029";

    protected BaseToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BaseToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<NewTokenEventResponse> getNewTokenEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("NewToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<StaticArray2<Uint256>>() {}, new TypeReference<Uint256>() {}, new TypeReference<StaticArray10<Uint256>>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NewTokenEventResponse> responses = new ArrayList<NewTokenEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NewTokenEventResponse typedResponse = new NewTokenEventResponse();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.dna = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.typeOf = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.location = (List<BigInteger>) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.theme = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.extras = (List<BigInteger>) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewTokenEventResponse> newTokenEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("NewToken", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<StaticArray2<Uint256>>() {}, new TypeReference<Uint256>() {}, new TypeReference<StaticArray10<Uint256>>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewTokenEventResponse>() {
            @Override
            public NewTokenEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NewTokenEventResponse typedResponse = new NewTokenEventResponse();
                typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.dna = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.typeOf = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.location = (List<BigInteger>) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.theme = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.extras = (List<BigInteger>) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
                return typedResponse;
            }
        });
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList());
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>> tokens(BigInteger param0) {
        final Function function = new Function("tokens", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>(
                new Callable<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple5<String, String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> tokenToOwner(BigInteger param0) {
        Function function = new Function("tokenToOwner", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> createRandomToken(String _name, String _typeOf, List<BigInteger> _location, BigInteger _theme, List<BigInteger> _extras, BigInteger _price) {
        Function function = new Function(
                "createRandomToken", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_typeOf), 
                new org.web3j.abi.datatypes.generated.StaticArray2<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_location, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_theme), 
                new org.web3j.abi.datatypes.generated.StaticArray10<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_extras, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(String newOwner) {
        Function function = new Function(
                "transferOwnership", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<BaseToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BaseToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<BaseToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BaseToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static BaseToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BaseToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static BaseToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BaseToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class NewTokenEventResponse {
        public BigInteger tokenId;

        public String name;

        public BigInteger dna;

        public String typeOf;

        public List<BigInteger> location;

        public BigInteger theme;

        public List<BigInteger> extras;

        public BigInteger price;
    }

    public static class OwnershipTransferredEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}
