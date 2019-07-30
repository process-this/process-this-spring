package io.github.processthis.springserver.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;

public interface LikeUserProfile {

   Date getCreated();

   @JsonSerialize(as = FlatUserProfile.class)
   UserProfile getUserProfile();

}
