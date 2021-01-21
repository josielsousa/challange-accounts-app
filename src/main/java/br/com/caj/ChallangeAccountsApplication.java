package br.com.caj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "br.com.caj")
@EnableMongoRepositories
public class ChallangeAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeAccountsApplication.class, args);
	}

}
