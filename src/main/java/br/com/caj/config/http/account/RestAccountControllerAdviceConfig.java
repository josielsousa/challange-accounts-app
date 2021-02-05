package br.com.caj.config.http.account;

import br.com.caj.config.http.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.caj.domain.usecase.exception.account.AccountException;
import br.com.caj.domain.usecase.exception.account.AccountExistingException;
import br.com.caj.domain.usecase.exception.account.AccountNotFoundException;

@RestControllerAdvice
public final class RestAccountControllerAdviceConfig {

  /**
   * Exception handle to capture default account constraint validation.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(AccountException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final AccountException e) {
    return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
  }

  /**
   * Exception handle to capture account not found exception.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final AccountNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
  }

  /**
   * Exception handle to capture account existing exception.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(AccountExistingException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final AccountExistingException e) {
    return ResponseEntity.unprocessableEntity().body(new ErrorMessage(e.getMessage()));
  }
}
