package com.Dyte.Log.Ingestor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogIngestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogIngestorApplication.class, args);
	}

}
