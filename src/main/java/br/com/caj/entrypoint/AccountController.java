package br.com.caj.entrypoint;

import java.io.Serializable;
import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.caj.domain.entity.Account;
import br.com.caj.domain.usecase.AccountUseCase;
import br.com.caj.entrypoint.model.AccountBalanceModel;
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
   * 
   * @param accountUseCase
   */
  public AccountController(final AccountUseCase accountUseCase) {
    this.accountUseCase = accountUseCase;
  }

  /**
   * Returns an set of all accounts avaliable.
   */
  @GetMapping("/accounts")
  public ResponseEntity<?> listAccounts() {
    final Set<AccountModel> accounts = accountUseCase.getAllAccounts().stream().map(AccountModel::fromDomain)
        .collect(Collectors.toSet());

    return ResponseEntity.ok(accounts);
  }

  /**
   * Return an account by uuid.
   * 
   * @param uuid
   * @return
   */
  @GetMapping("/account/{uuid}")
  public ResponseEntity<?> getAccount(
      @PathVariable(name = "uuid") @NotNull(message = "UUID not be empty") final String uuid) {
    final Account account = accountUseCase.getAccount(uuid);
    return ResponseEntity.ok(AccountModel.fromDomain(account));
  }

  /**
   * Return an account by uuid.
   * 
   * @param uuid
   * @return
   */
  @GetMapping("/account/{uuid}/balance")
  public ResponseEntity<?> getAccountBalance(
      @PathVariable("uuid") @NotBlank(message = "UUID not be empty") final String uuid) {
    final Account account = accountUseCase.getAccount(uuid);
    return ResponseEntity.ok(AccountBalanceModel.fromDomain(account));
  }

  /**
   * Create a new account from data received.
   * 
   * @param accountModel
   * @return
   */
  @PostMapping("/account")
  public ResponseEntity<?> create(@Valid @RequestBody final AccountModel accountModel) {
    final Account accountCreated = accountUseCase.create(AccountModel.toDomain(accountModel));
    final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}")
        .buildAndExpand(accountCreated.getUuid()).toUri();

    return ResponseEntity.created(location).build();
  }

  /**
   * Create a new account from data received.
   * 
   * @param accountModel
   * @return
   */
  @PutMapping("/account/{uuid}")
  public ResponseEntity<?> updated(@Valid @RequestBody final AccountModel accountModel,
      @PathVariable("uuid") final String uuid) {
    final Account accountUpdated = accountUseCase.update(AccountModel.toDomain(accountModel));
    return ResponseEntity.ok(AccountModel.fromDomain(accountUpdated));
  }
}
