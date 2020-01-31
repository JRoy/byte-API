package io.github.jroy.byteapi.http.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.jroy.byteapi.http.response.base.GenericResponse;
import io.github.jroy.byteapi.http.response.model.AuthenticateData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticateResponse extends GenericResponse {
  private AuthenticateData data;
}
