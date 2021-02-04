package br.com.caj.config.mongo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.caj.dataprovider.mongo.repository.AccountRepository;
import br.com.caj.dataprovider.mongo.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Profile("mongo")
@RequiredArgsConstructor
public class CustomPrepareDataConfig implements ApplicationRunner {

  private final AccountRepository accountRepository;
  private final TransferRepository transferRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (accountRepository.count() > 0) {
      return;
    }

    if (transferRepository.count() > 0) {
      return;
    }

    log.info("Mongo profile configured...");
  }
  
}
