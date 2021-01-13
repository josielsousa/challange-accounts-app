package br.com.caj.dataprovider;

import java.util.Set;

import javax.inject.Named;

import br.com.caj.domain.dataprovider.AccountProvider;
import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.exception.AccountException;
import lombok.RequiredArgsConstructor;

@Named("accountProviderImpl")
@RequiredArgsConstructor
public final class AccountDataProvider implements AccountProvider {

  @Override
  public Set<Account> getAllAccounts() throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account getAccount(String uuid) throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account create(Account account) throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account update(Account account) throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Account getAccountByCPF(String cpf) throws AccountException {
    // TODO Auto-generated method stub
    return null;
  }
  
}
