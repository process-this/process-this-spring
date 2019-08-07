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

import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SketchRepository extends CrudRepository<Sketch, UUID> {

  /**
   * This field creates an SQL query that finds all sketches that have likes and orders them in
   * order of the most likes it uses an inner join on the results of a nested query to accomplish
   * this magic
   */
  String FEATURED_QUERY =
      "SELECT "
          + "S.* "
          + "FROM SKETCH "
          + "AS S "
          + "INNER JOIN ( "
          + "SELECT "
          + "s_aggr.* "
          + "FROM ( "
          + "   SELECT SKETCH.SKETCH_ID, "
          + "count(*) AS count "
          + "FROM SKETCH "
          + "INNER JOIN LIKES "
          + "ON LIKES.SKETCH_ID = SKETCH.SKETCH_ID "
          + "GROUP BY SKETCH.SKETCH_ID "
          + ") AS s_aggr "
          + "ORDER BY s_aggr.count DESC "
          + "OFFSET 0 ROWS "
          + "FETCH next :count ROWS ONLY "
          + ") AS s_rank "
          + "ON s_rank.SKETCH_ID = S.SKETCH_ID ";


  /**
   * This method gets a list of all sketches ordered by name
   */
  List<Sketch> getAllByOrderByNameAsc();

  /**
   * This method gets a list of all sketches created a by a given user profile
   */
  List<Sketch> getAllByUserProfile(UserProfile userProfile);

  /**
   * This method is used to search for all sketches whose name contains a given fragment
   *
   * @param fragment string fragment used as a search criteria
   */
  List<Sketch> findAllByNameContainingOrderByNameAsc(String fragment);

  /**
   * This method returns a list of the last ten sketches created
   */
  List<Sketch> getTop10ByOrderByCreatedDesc();

  /**
   * This method ses the SQL constant defined above to return a list of sketches ordered by the most
   * likes
   *
   * @param count the number of sketches to return
   */
  @Query(value = FEATURED_QUERY, nativeQuery = true)
  List<Sketch> getMostLikedSketches(int count);


}
