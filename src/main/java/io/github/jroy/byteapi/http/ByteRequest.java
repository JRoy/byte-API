package io.github.jroy.byteapi.http;

import io.github.jroy.byteapi.ByteAPIException;
import io.github.jroy.byteapi.http.response.base.GenericResponse;
import io.github.jroy.byteapi.util.Constants;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.*;

import java.util.Objects;

public class ByteRequest {

  private final static String USER_AGENT = "byte/" + Constants.BYTE_VERSION + " (co.byte@trials; " +
      Constants.BYTE_TRAILS_NUMBER + "; Android " + Constants.ANDROID_SDK_VERSION + "/" + Constants.ANDROID_RELEASE + ") " +
      "okhttp/" + Constants.OKHTTP_VERSION;
  private final static MediaType JSON = MediaType.parse("application/json; charset=UTF-8");

  private static ByteRequestFactory requestFactory;

  @Getter
  private final String endpoint;
  private final Request.Builder request;

  private boolean requiresAuth = true;

  public ByteRequest(String endpoint) {
    this(Constants.API_URL, endpoint);
  }

  public ByteRequest(String url, String endpoint) {
    this.endpoint = endpoint;
    this.request = new Request.Builder().url(url + this.endpoint)
        .addHeader("User-Agent", USER_AGENT);
  }

  public ByteRequest addHeader(String header, String value) {
    this.request.addHeader(header, value);
    return this;
  }

  public ByteRequest setJsonPost(String payload) {
    this.request.post(RequestBody.create(payload, JSON));
    return this;
  }

  public ByteRequest setJsonPut(String payload) {
    this.request.put(RequestBody.create(payload, JSON));
    return this;
  }

  public ByteRequest requiresAuth(boolean requiresAuth) {
    this.requiresAuth = requiresAuth;
    return this;
  }

  private Call prepareCall() {
    if (requiresAuth) {
      if (requestFactory.getToken() == null) {
        throw new ByteAPIException("This request requires authentication!");
      }
      addHeader("authorization", requestFactory.getToken());
    }
    return requestFactory.getClient().newCall(request.build());
  }

  @SneakyThrows
  public void send() {
    prepareCall().execute();
  }

  public GenericResponse getResponse() {
    return getResponse(GenericResponse.class);
  }

  @SneakyThrows
  public <T> T getResponse(Class<T> responseClass) {
    @Cleanup Response response = prepareCall().execute();
    return requestFactory.mapResponse(responseClass, Objects.requireNonNull(response.body()).string());
  }

  protected static void setRequestFactory(ByteRequestFactory requestFactory) {
    ByteRequest.requestFactory = requestFactory;
  }
}
