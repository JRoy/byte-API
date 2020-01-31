package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Rebyte {
  private String id;
  private String authorID;
  private Long date;
  private Post post;
}
