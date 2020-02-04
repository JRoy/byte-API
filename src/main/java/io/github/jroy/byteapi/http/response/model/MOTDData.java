package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class MOTDData {
  private Integer version;
  private Object ios;
  private Object android;
}
