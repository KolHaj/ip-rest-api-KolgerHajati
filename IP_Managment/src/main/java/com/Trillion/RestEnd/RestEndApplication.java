package com.Trillion.RestEnd;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class RestEndApplication {
	/**
	 * Runs application and runs internal memory instantiation of H2 DB
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestEndApplication.class, args);
	}

}
