package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class AuthenticateData {
  private Token token;
  private Account account;
}
