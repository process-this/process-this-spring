package io.github.processthis.springserver.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;

/**
 * This interface was created to assist with the serialization of like objects
 */
public interface LikeUserProfile {

   /**This method gets the date a LikeUserProfile was created as Date
    * @return
    */
   Date getCreated();

   /**This method Gets user profiles serialized as flatUserprofiles
    * @return
    */
   @JsonSerialize(as = FlatUserProfile.class)
   UserProfile getUserProfile();

}
