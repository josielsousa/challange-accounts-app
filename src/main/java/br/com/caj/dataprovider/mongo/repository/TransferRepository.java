package br.com.caj.dataprovider.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.caj.dataprovider.mongo.model.TransferModel;

/**
 * Default interface to persist transfer data using spring data framework.
 */
@Repository
public interface TransferRepository extends MongoRepository<TransferModel, String> {

  List<TransferModel> findAllByAccountOriginUuid(final String accountOriginUuid);

}
