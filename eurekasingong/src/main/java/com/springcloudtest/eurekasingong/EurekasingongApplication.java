package com.springcloudtest.eurekasingong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableEurekaServer
public class EurekasingongApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekasingongApplication.class, args);
	}
}
