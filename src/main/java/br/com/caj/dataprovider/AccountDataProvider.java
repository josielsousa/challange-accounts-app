package br.com.caj.dataprovider;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

import javax.inject.Named;

import br.com.caj.dataprovider.mongo.model.AccountModel;
import br.com.caj.dataprovider.mongo.repository.AccountRepository;
import br.com.caj.domain.dataprovider.AccountProvider;
import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.exception.account.AccountException;
import br.com.caj.domain.usecase.exception.account.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

@Named("accountDataProvider")
@RequiredArgsConstructor
public final class AccountDataProvider implements AccountProvider {

  private final AccountRepository accountRepository;

  /**
   * Recovery all accounts existing into repository data.
   */
  public Set<Account> getAllAccounts() {
    return accountRepository.findAll()
          .stream()
          .map(AccountModel::toDomain)
          .collect(Collectors.toSet());
  }

  /**
   * Recovery account by uuid.
   */
  public Account getAccount(String uuid) throws AccountNotFoundException {
    final Optional<AccountModel> optionalAccountModel = accountRepository.findById(uuid);
    if (!optionalAccountModel.isPresent()) {
      return null;
    }

    Account account =  AccountModel.toDomain(optionalAccountModel.get());
    return account;
  }

  /**
   * Create a new account.
   */
  public Account create(Account account) throws AccountException {
    final AccountModel accountSaved = accountRepository.save(AccountModel.toPersist(account));
    return AccountModel.toDomain(accountSaved);
  }

  /**
   * Update an account existing.
   */
  public Account update(Account account) throws AccountException, AccountNotFoundException {
    Account exisitng = getAccount(account.getUuid());

    if (exisitng != null) {
      final AccountModel accountSaved = accountRepository.save(AccountModel.fromDomain(account));
      return AccountModel.toDomain(accountSaved);
    }
   
    return null;
  }

  /**
   * Recovery account by cpf.
   */
  public Account getAccountByCPF(final String cpf) {
    final Optional<AccountModel> optionalAccountModel = accountRepository.findByCpf(cpf);
    Account account = null;

    if (optionalAccountModel.isPresent()) {
      account = AccountModel.toDomain(optionalAccountModel.get());
    }

    return account;
  }
}
