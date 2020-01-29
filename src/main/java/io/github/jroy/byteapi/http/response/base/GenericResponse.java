package io.github.jroy.byteapi.http.response.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GenericResponse {
  private int success;
}
