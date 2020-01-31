package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Token {
  private String token;
  private String accountID;
  private Boolean isRegistered;
  private Boolean isDeactivated;
}
