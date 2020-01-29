package io.github.jroy.byteapi.http;

import io.github.jroy.byteapi.http.response.base.UncheckedObjectMapper;
import lombok.Getter;
import okhttp3.OkHttpClient;

public class ByteRequestFactory {

  @Getter
  private final OkHttpClient client = new OkHttpClient.Builder().build();
  private final UncheckedObjectMapper mapper = new UncheckedObjectMapper();

  public ByteRequest request(String endpoint) {
    return new ByteRequest(endpoint, this);
  }

  public <T> T mapResponse(Class<T> responseClass, String response) {
    return mapper.processResponse(responseClass, response);
  }
}
