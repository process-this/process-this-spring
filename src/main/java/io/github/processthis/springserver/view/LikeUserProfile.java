/*  Copyright [2019] [Asher Bearce, Jeffery Franken, Matthew Jones, Jennifer Nevares-Diaz]
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
   limitations under the License.
*/

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
