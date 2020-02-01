package io.github.jroy.byteapi;

import io.github.jroy.byteapi.http.ByteRequest;
import io.github.jroy.byteapi.http.ByteRequestFactory;
import io.github.jroy.byteapi.http.response.AuthenticateResponse;
import io.github.jroy.byteapi.http.response.RebytesTimelineResponse;
import io.github.jroy.byteapi.http.response.SelfAccountInfoResponse;
import io.github.jroy.byteapi.http.response.TimelineResponse;
import lombok.Getter;
import org.json.JSONObject;

public class Byte {

  private boolean debug;
  private final ByteRequestFactory requestFactory;

  private boolean isLoggedIn = false;
  @Getter
  private String token = null;
  @Getter
  private String accountId = null;

  public Byte() {
    this(false);
  }

  public Byte(boolean debug) {
    this.debug = debug;
    this.requestFactory = new ByteRequestFactory(this);
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
    return new ByteRequest("timeline")
        .getResponse(TimelineResponse.class);
  }

  public TimelineResponse getGlobalFeed() {
    return new ByteRequest("feed/global")
        .getResponse(TimelineResponse.class);
  }

  public RebytesTimelineResponse getRebytesTimeline() {
    return new ByteRequest("timeline/rebytes")
        .getResponse(RebytesTimelineResponse.class);
  }

  public SelfAccountInfoResponse getSelfAccount() {
    return new ByteRequest("account/me")
        .getResponse(SelfAccountInfoResponse.class);
  }

  private void sendAppOpen() {
    new ByteRequest("client/event")
        .requiresAuth(false)
        .setJsonPost("{\"eventData\":{\"os\":\"android\"},\"eventType\":\"appOpen\"}")
        .send();
  }
}
