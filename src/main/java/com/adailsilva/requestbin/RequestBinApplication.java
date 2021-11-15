package com.adailsilva.requestbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequestBinApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestBinApplication.class, args);

		RequestBin requestBin = new RequestBin();

		String responsePost = requestBin.post();
		String responseGet = requestBin.get();

		System.out.println(responsePost);
		System.out.println(responseGet);

	}

}
