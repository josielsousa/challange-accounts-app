package br.com.caj.dataprovider;

import java.util.Set;
import java.util.Collections;

import javax.inject.Named;

import br.com.caj.domain.dataprovider.AccountProvider;
import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.exception.AccountException;
import br.com.caj.domain.usecase.exception.AccountExistingException;
import br.com.caj.domain.usecase.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

@Named("accountDataProvider")
@RequiredArgsConstructor
public final class AccountDataProvider implements AccountProvider {

  public Set<Account> getAllAccounts() throws AccountException {
    return Collections.emptySet();
  }

  public Account getAccount(String uuid) throws AccountNotFoundException {
    return null;
  }

  public Account create(Account account) throws AccountException, AccountExistingException {
    return Account.builder().build();
  }

  public Account update(Account account) throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }

  public Account getAccountByCPF(String cpf) throws AccountException {
    return null;
  }
}
