package br.com.caj.dataprovider.mongo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.caj.domain.entity.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Mapping data to persist account model info.
 */
@Data
@Builder
@Document("accounts")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class AccountModel implements Serializable {

  private static final long serialVersionUID = 1099699127320428177L;

  @Id
  private String uuid;

  private String cpf;
  private String name;
  private String secret;
  private BigDecimal balance;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
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
      .cpf(accountModel.getCpf())
      .uuid(accountModel.getUuid())
      .secret(accountModel.getSecret())
      .name(accountModel.getName())
      .balance(accountModel.getBalance())
      .createdAt(accountModel.getCreatedAt())
      .updatedAt(accountModel.getUpdatedAt())
      .build();
  }
}
