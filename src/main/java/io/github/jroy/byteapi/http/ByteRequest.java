package io.github.jroy.byteapi.http;

import io.github.jroy.byteapi.http.response.base.GenericResponse;
import io.github.jroy.byteapi.util.Constants;
import lombok.Cleanup;
import lombok.Getter;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

  public ByteRequest(String endpoint) {
    this.endpoint = endpoint;
    this.request = new Request.Builder().url(Constants.API_URL + this.endpoint)
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

  public ByteRequest authorization(String token) {
    addHeader("authorization", token);
    return this;
  }

  @SneakyThrows
  public void send() {
    requestFactory.getClient().newCall(request.build()).execute();
  }

  public GenericResponse getResponse() {
    return getResponse(GenericResponse.class);
  }

  @SneakyThrows
  public <T> T getResponse(Class<T> responseClass) {
    @Cleanup Response response = requestFactory.getClient().newCall(request.build()).execute();
    return requestFactory.mapResponse(responseClass, Objects.requireNonNull(response.body()).string());
  }

  protected static void setRequestFactory(ByteRequestFactory requestFactory) {
    ByteRequest.requestFactory = requestFactory;
  }
}
