package io.github.jroy.byteapi;

import io.github.jroy.byteapi.http.ByteRequest;
import io.github.jroy.byteapi.http.ByteRequestFactory;
import io.github.jroy.byteapi.http.response.AuthenticateResponse;
import org.json.JSONObject;

public class Byte {

  private boolean debug;
  private final ByteRequestFactory requestFactory;

  private boolean isLoggedIn = false;
  private String token;
  private String accountId;

  public Byte() {
    this(false);
  }

  public Byte(boolean debug) {
    this.debug = debug;
    this.requestFactory = new ByteRequestFactory();
  }

  public AuthenticateResponse login(String googleToken) {
    if (isLoggedIn) {
      throw new ByteAPIException("Already logged in!");
    }
    sendAppOpen();
    AuthenticateResponse response = new ByteRequest("authenticate/google")
        .setJsonPost(new JSONObject().put("provider", "google").put("token", googleToken).toString())
        .getResponse(AuthenticateResponse.class);
    if (!response.isSuccess()) {
      throw new ByteAPIException("Error while logging into byte!");
    }
    isLoggedIn = true;
    token = response.getData().getToken().getToken();
    accountId = response.getData().getToken().getAccountID();
    return response;
  }

  private void sendAppOpen() {
    new ByteRequest("client/event")
        .setJsonPost("{\"eventData\":{\"os\":\"android\"},\"eventType\":\"appOpen\"}")
        .send();
  }
}
