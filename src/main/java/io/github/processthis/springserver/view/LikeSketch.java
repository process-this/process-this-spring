package io.github.processthis.springserver.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;

/**
 * This interface was created to facilitate json serialization of like objects
 */
public interface LikeSketch {


  /**
   * This method is silly, but it gets the date a likeSketch was created.
   */
  Date getCreated();


  /**
   * This method defines that the getsketch method will be serialized as a flatSketch, preventing
   * self-referential serialization
   */
  @JsonSerialize(as = FlatSketch.class)
  Sketch getSketch();

}
