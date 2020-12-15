package com.filippau.tc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.filippau"})
public class TransportCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransportCompanyApplication.class, args);
	}

}
