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

package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Like;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface implements the CrudRepository, autogenerating SQL queries based on the form of the
 * method name. Can you call it black magic if used for the purposes of good?
 */
public interface LikeRepository extends CrudRepository<Like, UUID> {

  /**
   * This method queries all likes by the date created
   */
  List<Like> getAllByOrderByCreated();

  /**
   * This method gts a list of all likes created by a given userprofile
   */
  List<Like> getAllByUserProfileOrderByCreatedAsc(UserProfile userProfile);

  /**
   * This method gets a list of all likes asccociated by a given sketch ordered by date created
   */
  List<Like> getAllBySketchOrderByCreatedAsc(Sketch sketch);

  /**
   * This method retrives a like between a specific user and sketch
   */
  Optional<Like> findLikeByUserProfileAndSketch(UserProfile userProfile, Sketch sketch);

  /**
   * This method deletes a like between a given user and sketch
   */
  void deleteLikeByUserProfileAndSketch(UserProfile userProfile, Sketch sketch);

  /**
   * This method deletes all like associated with a user (for use when a userProfile is deleted)
   */
  void deleteLikesByUserProfile(UserProfile userProfile);

  /**
   * This method deletes all likes associated with a sketch (for use when a given sketch is
   * deleted)
   */
  void deleteLikesBySketch(Sketch sketch);


}
