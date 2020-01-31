package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Account {
  private String id;
  private Boolean isRegistered;
  private Boolean isChannel;
  private Boolean isSuspended;
  private Boolean isDeactivated;
  private Long registrationDate;
  private String username;
  private String displayName;
  private String avatarURL;
  private String bio;
  private String backgroundColor;
  private String foregroundColor;
  private Integer followerCount;
  private Integer followingCount;
  private Integer loopCount;
  private Integer loopsConsumedCount;
  private Boolean isFollowing;
  private Boolean isFollowed;
  private Boolean isBlocked;
}
