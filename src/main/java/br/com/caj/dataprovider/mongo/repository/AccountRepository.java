package br.com.caj.dataprovider.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.caj.dataprovider.mongo.model.AccountModel;

/**
 * Default interface to persist account data using spring data framework.
 */
@Repository
public interface AccountRepository extends MongoRepository<AccountModel, String> {

  Optional<AccountModel> findByCpf(final String cpf);

}
