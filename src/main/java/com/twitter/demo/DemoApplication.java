package com.twitter.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String apiKey = "6bMIUFw5XtmsYeMem0L3S2pN9";
		String apiSecret = "EfYKEHP82Q9BGfu2HQAvLumw6BoFS1xC5DKI18YPBhJkRTDmjm";
		String accessToken = "1431209079184101379-Na8B5Gp1E2hFaXIxfQuWvDt5uFPcUK";
		String accessTokenSecret = "sVYWERSSbxnWnil8ixqez6bf1jhaMoft5r5qkNkwDWvCE";

	}

}
