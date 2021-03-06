package br.com.caj.entrypoint.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caj.domain.entity.Account;
import lombok.Builder;
import lombok.Data;

/**
 * Account model used on transfer account data to account domain.
 */
@Builder
@Data
public final class AccountModel implements Serializable {

  private static final long serialVersionUID = -6350487785860680151L;

  private String uuid;

  @NotBlank(message = "Name not be empty")
  private String name;

  @NotBlank(message = "CPF not be empty")
  private String cpf;

  @NotBlank(message = "Secret not be empty")
  private String secret;

  @DecimalMin(value = "0.01", message = "Balance not be empty")
  private BigDecimal balance;

  private Instant createdAt;
  private Instant updatedAt;

  /**
   * Checks that uuid is empty.
   */
  private boolean uuidIsEmpty() {
    return uuid == null || uuid.isBlank();
  }

  /**
   * Checks that secret is empty.
   */
  private boolean secretIsEmpty() {
    return secret == null || secret.isBlank();
  }

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
    final String uuid = accountModel.uuidIsEmpty() ? UUID.randomUUID().toString() : accountModel.getUuid();

    String secretHashed = null;
    if (!accountModel.secretIsEmpty()) {
      secretHashed = new BCryptPasswordEncoder().encode(accountModel.getSecret());
    }

    return Account.builder()
      .uuid(uuid)
      .secret(secretHashed)
      .cpf(accountModel.getCpf())
      .name(accountModel.getName())
      .balance(accountModel.getBalance())
      .createdAt(accountModel.getCreatedAt())
      .updatedAt(accountModel.getUpdatedAt())
      .build();
  }
}
