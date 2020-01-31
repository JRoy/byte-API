package io.github.jroy.byteapi.http.response.model;

import lombok.Data;

@Data
public class RebytesTimelineData {
  private Rebyte[] rebytes;
  private String cursor;
  private Account[] accounts;
}
