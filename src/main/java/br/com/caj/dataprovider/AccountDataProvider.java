package br.com.caj.dataprovider;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;

import javax.inject.Named;

import br.com.caj.dataprovider.mongo.model.AccountModel;
import br.com.caj.dataprovider.mongo.repository.AccountRepository;
import br.com.caj.domain.dataprovider.AccountProvider;
import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.exception.AccountException;
import br.com.caj.domain.usecase.exception.AccountExistingException;
import br.com.caj.domain.usecase.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

@Named("accountDataProvider")
@RequiredArgsConstructor
public final class AccountDataProvider implements AccountProvider {

  private final AccountRepository accountRepository;

  /**
   * Recovery all accounts existing into repository data.
   */
  public Set<Account> getAllAccounts() throws AccountException {
    return accountRepository.findAll()
          .stream()
          .map(AccountModel::toDomain)
          .collect(Collectors.toSet());
  }

  public Account getAccount(String uuid) throws AccountNotFoundException {
    return Account.builder().build();
  }

  public Account create(Account account) throws AccountException, AccountExistingException {
    return Account.builder().build();
  }

  public Account update(Account account) throws AccountException, AccountNotFoundException {
    return account;
  }

  public Account getAccountByCPF(String cpf) throws AccountException {
    return null;
  }
}
