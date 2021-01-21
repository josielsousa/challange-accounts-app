package br.com.caj.dataprovider.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.caj.dataprovider.mongo.model.AccountModel;

/**
 * Default interface to persist account data using spring data framework.
 */
@Repository
public interface AccountRepository extends MongoRepository<AccountModel, String> {
  
}
