package io.github.jroy.byteapi.http.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GenericResponse {
  private Integer success;

  public boolean isSuccess() {
    return success != null && success == 1;
  }
}
