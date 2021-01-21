package br.com.caj.config.mongo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.caj.dataprovider.mongo.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("mongo")
public class CustomPrepareDataConfig implements ApplicationRunner {

  private final AccountRepository accountRepository;

  /**
   * Default constructor.
   */
  public CustomPrepareDataConfig(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (accountRepository.count() > 0) {
      return;
    }

    log.info("Mongo profile configured...");
  }
  
}
