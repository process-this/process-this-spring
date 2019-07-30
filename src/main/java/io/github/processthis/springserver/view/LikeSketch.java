package io.github.processthis.springserver.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;

public interface LikeSketch {


  Date getCreated();



  @JsonSerialize(as = FlatSketch.class)
  Sketch getSketch();

}
