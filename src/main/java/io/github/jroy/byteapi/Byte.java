package io.github.jroy.byteapi;

import io.github.jroy.byteapi.http.ByteRequest;
import io.github.jroy.byteapi.http.ByteRequestFactory;
import io.github.jroy.byteapi.http.response.AuthenticateResponse;
import io.github.jroy.byteapi.http.response.RebytesTimelineResponse;
import io.github.jroy.byteapi.http.response.SelfAccountInfoResponse;
import io.github.jroy.byteapi.http.response.TimelineResponse;
import org.json.JSONObject;

public class Byte {

  private boolean debug;
  private final ByteRequestFactory requestFactory;

  private boolean isLoggedIn = false;
  private String token = null;
  private String accountId = null;

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
    getTimeline();
    getGlobalFeed();
    getRebytesTimeline();
    getSelfAccount();
    //TODO: Find deviceToken code in https://api.byte.co/account/me/device
    return response;
  }

  public TimelineResponse getTimeline() {
    if (!isLoggedIn) {
      throw new ByteAPIException("This request requires authentication!");
    }
    return new ByteRequest("timeline")
        .authorization(token)
        .getResponse(TimelineResponse.class);
  }

  public TimelineResponse getGlobalFeed() {
    if (!isLoggedIn) {
      throw new ByteAPIException("This request requires authentication!");
    }
    return new ByteRequest("feed/global")
        .authorization(token)
        .getResponse(TimelineResponse.class);
  }

  public RebytesTimelineResponse getRebytesTimeline() {
    if (!isLoggedIn) {
      throw new ByteAPIException("This request requires authentication!");
    }
    return new ByteRequest("timeline/rebytes")
        .authorization(token)
        .getResponse(RebytesTimelineResponse.class);
  }

  public SelfAccountInfoResponse getSelfAccount() {
    if (!isLoggedIn) {
      throw new ByteAPIException("This request requires authentication!");
    }
    return new ByteRequest("account/me")
        .authorization(token)
        .getResponse(SelfAccountInfoResponse.class);
  }

  private void sendAppOpen() {
    new ByteRequest("client/event")
        .setJsonPost("{\"eventData\":{\"os\":\"android\"},\"eventType\":\"appOpen\"}")
        .send();
  }
}
