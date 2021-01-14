package br.com.caj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.caj")
public class ChallangeAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeAccountsApplication.class, args);
	}

}
