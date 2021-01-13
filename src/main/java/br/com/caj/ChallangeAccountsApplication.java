package br.com.caj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.caj.domain")
public class ChallangeAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallangeAccountsApplication.class, args);
	}

}
