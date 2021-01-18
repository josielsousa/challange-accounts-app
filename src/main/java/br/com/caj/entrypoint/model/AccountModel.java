package br.com.caj.entrypoint.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import br.com.caj.domain.entity.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Account model used on transfer account data to account domain.
 */
@Builder
@Data
public final class AccountModel implements Serializable {

  private static final long serialVersionUID = 4921707232595598380L;

  private String uuid;
  private String name;
  private String cpf;
  private String secret;
  private BigDecimal balance;
  private Instant createdAt;
  private Instant updatedAt;

  /**
   * Transform account domain into account app model.
   * 
   * @param account
   * @return
   */
  public static AccountModel fromDomain(final Account account) {
    return AccountModel.builder()
      .uuid(account.getUuid())
      .cpf(account.getCpf())
      .name(account.getName())
      .secret(account.getSecret())
      .balance(account.getBalance())
      .createdAt(account.getCreatedAt())
      .updatedAt(account.getUpdatedAt())
      .build();
  }

  /**
   * Transform account app into account domain.
   * 
   * @param account
   * @return
   */
  public static Account toDomain(final AccountModel accountModel) {
    return Account.builder()
      .uuid(accountModel.getUuid())
      .cpf(accountModel.getCpf())
      .name(accountModel.getName())
      .secret(accountModel.getSecret())
      .balance(accountModel.getBalance())
      .createdAt(accountModel.getCreatedAt())
      .updatedAt(accountModel.getUpdatedAt())
      .build();
  }
}
