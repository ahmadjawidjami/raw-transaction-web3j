package com.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class SolidityWeb3jApplication {

	public static void main(String[] args) throws IOException, CipherException, ExecutionException, InterruptedException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

	//	Web3j web3 = Web3j.build(new HttpService("52.15.82.168:8545"));  // defaults to http://localhost:8545/
		SpringApplication.run(SolidityWeb3jApplication.class, args);

		new RawTransactionManager().createRawTransaction();




	}
}
