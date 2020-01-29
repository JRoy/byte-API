package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Mention {
  private String accountId;
  private String username;
  private String text;
  private Range range;
  private Range byteRange;
}
