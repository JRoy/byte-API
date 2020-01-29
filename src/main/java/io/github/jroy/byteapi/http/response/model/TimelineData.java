package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class TimelineData {
  private Post[] posts;
  private String cursor;
  private LinkedHashMap<String, Account> accounts;
}
