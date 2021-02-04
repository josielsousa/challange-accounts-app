package br.com.caj.entrypoint.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.caj.domain.entity.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Account balance model used on transfer account data to account domain.
 */
@Builder
@Data
public final class AccountBalanceModel implements Serializable {

  private static final long serialVersionUID = 1381879402378634874L;

  private String uuid;
  private BigDecimal balance;

  /**
   * Transform account domain into account app model.
   * 
   * @param account
   * @return
   */
  public static AccountBalanceModel fromDomain(final Account account) {
    return AccountBalanceModel.builder()
      .uuid(account.getUuid())
      .balance(account.getBalance())
      .build();
  }
}
