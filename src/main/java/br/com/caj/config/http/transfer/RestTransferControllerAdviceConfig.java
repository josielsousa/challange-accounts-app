package br.com.caj.config.http.transfer;

import br.com.caj.config.http.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.caj.domain.usecase.exception.transfer.TransferException;
import br.com.caj.domain.usecase.exception.transfer.TransferInsufficientBalanceException;
import br.com.caj.domain.usecase.exception.transfer.TransferNotFoundException;

@RestControllerAdvice
public final class RestTransferControllerAdviceConfig {

  /**
   * Exception handle to capture default transfer constraint validation.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(TransferException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final TransferException e) {
    return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
  }

  /**
   * Exception handle to capture account exception not found on transfer action.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(TransferNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final TransferNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
  }

  /**
   * Exception handle to capture account balance insufficient exception.
   * 
   * @param e
   * @return
   */
  @ExceptionHandler(TransferInsufficientBalanceException.class)
  public ResponseEntity<ErrorMessage> handleValidationExceptions(final TransferInsufficientBalanceException e) {
    return ResponseEntity.unprocessableEntity().body(new ErrorMessage(e.getMessage()));
  }
}
