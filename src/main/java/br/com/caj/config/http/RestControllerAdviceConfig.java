package br.com.caj.config.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class RestControllerAdviceConfig {

  /**
   * Exception handle to capture default constraint validation.
   * 
   * @param e
   * @return
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(final MethodArgumentNotValidException e) {

    Map<String, String> errors = new HashMap<String, String>();
    e.getBindingResult().getAllErrors().forEach(error -> {
      final String fieldName = ((FieldError) error).getField();
      final String message = error.getDefaultMessage();
      errors.put(fieldName, message);
    });

    return errors;
  }
}
