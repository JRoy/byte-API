package io.github.jroy.byteapi.http.response.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.CompletionException;

public class UncheckedObjectMapper extends ObjectMapper {

  public UncheckedObjectMapper() {
    enable(MapperFeature.AUTO_DETECT_FIELDS);
  }

  public <T> T processResponse(Class<T> response, String content) {
    try {
      return this.readValue(content, getTypeFactory().constructType(response));
    } catch (JsonProcessingException e) {
      throw new CompletionException(e);
    }
  }
}
