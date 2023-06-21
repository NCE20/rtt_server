package com.example.rtt_server;

import com.example.rtt_server.util.DatabaseConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RttServerApplication {

	public static void main(String[] args) {
		//pringApplication.run(RttServerApplication.class, args);
		DatabaseConnector.testConnection();
	}

}
