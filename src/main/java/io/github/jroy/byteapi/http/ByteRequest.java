package io.github.jroy.byteapi.http;

import io.github.jroy.byteapi.util.Constants;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Objects;

public class ByteRequest {

  @Getter
  private final String endpoint;
  private final ByteRequestFactory requestFactory;
  private final Request.Builder request;

  public ByteRequest(String endpoint, ByteRequestFactory requestFactory) {
    this.endpoint = endpoint;
    this.requestFactory = requestFactory;
    this.request = new Request.Builder().url(Constants.API_URL + this.endpoint);
  }

  @SneakyThrows
  public void send() {
    requestFactory.getClient().newCall(request.build()).execute();
  }

  @SneakyThrows
  public <T> T getResponse(Class<T> responseClass) {
    @Cleanup Response response = requestFactory.getClient().newCall(request.build()).execute();
    return requestFactory.mapResponse(responseClass, Objects.requireNonNull(response.body()).string());
  }
}
