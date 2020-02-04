package io.github.jroy.byteapi;

import io.github.jroy.byteapi.http.response.TimelineResponse;
import io.github.jroy.byteapi.http.response.base.GenericResponse;
import io.github.jroy.byteapi.http.response.base.UncheckedObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseTest {

  @Test
  public void testGenericResponse() {
    GenericResponse response = new UncheckedObjectMapper().processResponse(GenericResponse.class, "{\"data\":{},\"success\":12}");
    assertEquals(Integer.valueOf(12), response.getSuccess());
  }

  @Test
  public void testTimelineResponse() {
    TimelineResponse response = new UncheckedObjectMapper().processResponse(TimelineResponse.class, "{\"data\":{\"posts\":[{\"id\":\"dsgsdgfdsgf\",\"type\":0,\"authorID\":\"sdfgdsgdsfg\",\"caption\":\"Test Caption\",\"allowCuration\":true,\"allowRemix\":false,\"category\":\"comedy\",\"mentions\":[],\"date\":1580244927,\"videoSrc\":\"dgfsdgfgfdsf\",\"thumbSrc\":\"sdgfgsdfg\",\"commentCount\":2,\"comments\":[{\"id\":\"ffff-ffff\",\"postID\":\"gggg\",\"authorID\":\"ggggg\",\"body\":\"Yeeeeah\",\"mentions\":[],\"date\":1580330163},{\"id\":\"gdfgdfgd-gfg\",\"postID\":\"fgfgfgfgf\",\"authorID\":\"dfgdsgdsg\",\"body\":\"Nice\",\"mentions\":[],\"date\":1585555052}],\"likeCount\":24,\"likedByMe\":false,\"loopCount\":91,\"rebytedByMe\":false}],\"cursor\":\"CXXCergegeWA\",\"accounts\":{\"rgrgrrgr\":{\"id\":\"rrgrggrrgrggre\",\"isChannel\":false,\"registrationDate\":15344444983320,\"username\":\"dddd\",\"displayName\":\"Tilda\",\"avatarURL\":\"dfsgsdfgd\",\"bio\":\"I want\",\"backgroundColor\":\"#326366\",\"foregroundColor\":\"#FFAAA5\",\"followerCount\":0,\"followingCount\":0,\"loopCount\":0,\"loopsConsumedCount\":0,\"isFollowing\":false,\"isFollowed\":false,\"isBlocked\":false}}},\"success\":1}");
    assertEquals(response.getData().getAccounts().values().iterator().next().getId(), "rrgrggrrgrggre");
    assertEquals(Integer.valueOf(1), response.getSuccess());
  }
}
