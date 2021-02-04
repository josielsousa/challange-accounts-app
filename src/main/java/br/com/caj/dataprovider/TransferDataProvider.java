package br.com.caj.dataprovider;

import java.util.Set;
import java.util.HashSet;

import javax.inject.Named;

import br.com.caj.domain.dataprovider.TransferProvider;
import br.com.caj.domain.entity.Transfer;
import br.com.caj.domain.usecase.exception.account.AccountException;
import lombok.RequiredArgsConstructor;

@Named("transferDataProvider")
@RequiredArgsConstructor
public final class TransferDataProvider implements TransferProvider {

  // private final TransferRepository transferRepository;

  /**
   * Recovery all accounts existing into repository data.
   */
  public Set<Transfer> getAllTransfers(String accountUuid) {
    // return transferRepository.findAll()
    // .stream()
    // .map(AccountModel::toDomain)
    // .collect(Collectors.toSet());
    return new HashSet<Transfer>();
  }

  /**
   * Create a new transfer.
   */
  public Transfer create(Transfer transfer) throws AccountException {
    // save on transfer repository
    return Transfer.builder().build();
  }
}
