package com.rpgdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class RpgDatabaseApplication {

	  public static void main(String[] args) {
	    SpringApplication.run(RpgDatabaseApplication.class, args);
	  }

}
