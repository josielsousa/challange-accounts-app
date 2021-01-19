package br.com.caj.entrypoint;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.caj.config.http.ErrorMessage;
import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.AccountUseCase;
import br.com.caj.domain.usecase.exception.AccountException;
import br.com.caj.domain.usecase.exception.AccountNotFoundException;
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
  public ResponseEntity<?> listAccounts() {
    try {
      final Set<AccountModel> accounts = accountUseCase.getAllAccounts().stream().map(AccountModel::fromDomain)
          .collect(Collectors.toSet());

      return ResponseEntity.ok(accounts);
    } catch (AccountException e) {
      return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e.getMessage()));
    }
  }

  /**
   * Return an account by uuid.
   */
  @GetMapping("/accounts/{uuid}")
  public ResponseEntity<?> getAccount(@PathVariable("uuid") final String uuid) {
    try {
      final Account account = accountUseCase.getAccount(uuid);
      return ResponseEntity.ok(account);
    } catch (AccountNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(e.getMessage()));
    }
  }
}
