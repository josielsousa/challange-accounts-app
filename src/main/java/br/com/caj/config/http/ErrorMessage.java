package br.com.caj.config.http;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Default struct of error message to use into app.
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
  private String message;
}