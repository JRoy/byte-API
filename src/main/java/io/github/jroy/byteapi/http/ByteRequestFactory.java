package io.github.jroy.byteapi.http;

import okhttp3.OkHttpClient;

public class ByteRequestFactory {

  private final OkHttpClient client = new OkHttpClient.Builder().build();

  public ByteRequest request(String endpoint) {
    return new ByteRequest();
  }
}
