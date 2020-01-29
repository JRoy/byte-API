package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class Post {
  private String id;
  private Integer type;
  private String authorID;
  private String caption;
  private Boolean allowCuration;
  private Boolean allowRemix;
  private String category;
  private Mention[] mentions;
  private Long date;
  private String videoSrc;
  private String thumbSrc;
  private Integer commentCount;
  private Comment[] comments;
  private Integer likeCount;
  private Boolean likedByMe;
  private Integer loopCount;
  private Boolean rebytedByMe;
}
