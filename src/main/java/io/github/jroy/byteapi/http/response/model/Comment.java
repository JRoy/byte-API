package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Comment {
  private String id;
  private String postID;
  private String authorID;
  private String body;
  private Mention[] mentions;
  private Long date;
}
