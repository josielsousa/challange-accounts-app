package br.com.caj.entrypoint;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caj.config.http.ErrorMessage;
import br.com.caj.domain.usecase.AccountUseCase;
import br.com.caj.domain.usecase.exception.AccountException;
import br.com.caj.entrypoint.model.AccountModel;

/**
 * Accounts endpoint controller.
 */
@RestController
public final class AccountController implements Serializable {

  private static final long serialVersionUID = 2197682277182980497L;

  private AccountUseCase accountUseCase;

  /**
   * Default constructor.
   */
  public AccountController(final AccountUseCase accountUseCase) {
    this.accountUseCase = accountUseCase;
  }

  /**
   * Returns an set of all accounts avaliable.
   */
  @GetMapping("/accounts")
  public ResponseEntity<Set<AccountModel>> listAccounts() {
    Set<AccountModel> accounts = Collections.emptySet();

    try {
      accounts = accountUseCase.getAllAccounts().stream().map(AccountModel::fromDomain).collect(Collectors.toSet());
    } catch (AccountException e) {
      ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    } catch (Exception e) {
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e.getMessage()));
    }

    return ResponseEntity.ok(accounts);
  }
}
