package com.yuziak.Hotelshi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.yuziak.Hotelshi"})
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
