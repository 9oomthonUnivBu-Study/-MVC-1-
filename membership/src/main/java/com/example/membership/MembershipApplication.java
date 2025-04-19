package com.example.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MembershipApplication {

	public static void main(String[] args) {
		SpringApplication.run(MembershipApplication.class, args);
	}


}
