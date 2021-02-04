package br.com.caj.dataprovider;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;

import br.com.caj.dataprovider.mongo.model.TransferModel;
import br.com.caj.dataprovider.mongo.repository.TransferRepository;
import br.com.caj.domain.dataprovider.TransferProvider;
import br.com.caj.domain.entity.Transfer;
import lombok.RequiredArgsConstructor;

@Named("transferDataProvider")
@RequiredArgsConstructor
public final class TransferDataProvider implements TransferProvider {

  private final TransferRepository transferRepository;

  /**
   * Recovery all transfers existing for account id into repository data.
   */
  public Set<Transfer> getAllTransfers(final String accountOriginUuid) {
    return transferRepository.findAllByAccountOriginUuid(accountOriginUuid)
    .stream()
    .map(TransferModel::toDomain)
    .collect(Collectors.toSet());
  }

  /**
   * Create a new transfer.
   */
  public Transfer create(Transfer transfer) {
    final TransferModel transferSaved = transferRepository.save(TransferModel.fromDomain(transfer));
    return TransferModel.toDomain(transferSaved);
  }
}
