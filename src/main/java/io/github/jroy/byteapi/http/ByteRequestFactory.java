package io.github.jroy.byteapi.http;

import io.github.jroy.byteapi.Byte;
import io.github.jroy.byteapi.http.response.base.UncheckedObjectMapper;
import lombok.Getter;
import okhttp3.OkHttpClient;

public class ByteRequestFactory {

  @Getter
  private final OkHttpClient client = new OkHttpClient.Builder().build();
  private final UncheckedObjectMapper mapper = new UncheckedObjectMapper();
  private final Byte parent;

  public ByteRequestFactory(Byte parent) {
    this.parent = parent;
    ByteRequest.setRequestFactory(this);
  }

  public <T> T mapResponse(Class<T> responseClass, String response) {
    return mapper.processResponse(responseClass, response);
  }

  public String getToken() {
    return parent.getToken();
  }

  public String getAccountId() {
    return parent.getAccountId();
  }
}
