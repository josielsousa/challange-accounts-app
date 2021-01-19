package br.com.caj.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Custom web security configurer adapter.
 */
@EnableWebSecurity
@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Override spring security implementation to disable default CSRF (cross-site request forgery).
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
  }
}
