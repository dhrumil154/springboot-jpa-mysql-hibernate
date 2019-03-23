package com.kjit.Diekraft;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EntityScan(basePackages="com.kjit.Diekraft.entity")

public class DiekraftServices {

	public static void main(String[] args) {
		
		
		SpringApplication.run(DiekraftServices.class, args);
		System.out.println("Application started");
	}
}
