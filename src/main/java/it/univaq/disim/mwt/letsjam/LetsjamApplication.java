package it.univaq.disim.mwt.letsjam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LetsjamApplication {
	//main function
	public static void main(String[] args) {
		SpringApplication.run(LetsjamApplication.class, args);
		System.out.println("--------------- READY -------------");
	}

}
