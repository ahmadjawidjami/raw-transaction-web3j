package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthSign;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.infura.InfuraHttpService;
import org.web3j.protocol.parity.Parity;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.ManagedTransaction.GAS_PRICE;

/**
 * Created by ahmadjawid on 7/16/17.
 */
public class RawTransactionManager {


//    @Autowired
//    Web3j web3j;

//    @Autowired
//    Parity parity;


    public String createRawTransaction() throws IOException, CipherException, ExecutionException, InterruptedException {
        Web3j web3j = Web3j.build(new HttpService());

        String walletFile = "./UTC--2017-07-10T14-28-31.729000000Z--87dfd2200b2fab601fb53fb32877c352f293756e.json";

       Credentials credentials = WalletUtils.loadCredentials("test", walletFile);

        HelloWorld contract = HelloWorld.load(
                "0x894E59f34954c752F4504ADD482482471937c172", web3j, credentials, GAS_PRICE, GAS_LIMIT);

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();

        BigInteger nonce = ethGetTransactionCount.getTransactionCount();


        Function function = new Function("setMessage", Arrays.<Type>asList(new Utf8String("this message is the parameter of 'setMessage' function call" +
                "")), Collections.<TypeReference<?>>emptyList());

       // System.out.println(FunctionEncoder.encode(function));

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce, GAS_PRICE, GAS_LIMIT,
                "0x894E59f34954c752F4504ADD482482471937c172",
                FunctionEncoder.encode(function));


        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);


       EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();



        System.out.println(ethSendTransaction.getTransactionHash());



 //       System.out.println(contract.setMessage(new Utf8String("hello message")));
        System.out.println(contract.message().get());




        return null;
    }
}
