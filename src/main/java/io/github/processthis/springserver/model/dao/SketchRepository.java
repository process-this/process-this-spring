package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SketchRepository extends CrudRepository <Sketch, UUID> {

   String FEATURED_QUERY =
      "SELECT "
          + "S.* "
          +  "FROM SKETCH "
          +  "AS S "
          + "INNER JOIN ( "
          +"SELECT "
          +"s_aggr.* "
          +"FROM ( "
          +"   SELECT SKETCH.SKETCH_ID, "
          +"count(*) AS count "
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


  List<Sketch> getAllByOrderByNameAsc();

  List<Sketch> getAllByUserProfile(UserProfile userProfile);

  List<Sketch> findAllByNameContainingOrderByNameAsc(String fragment);

  List<Sketch> getTop10ByOrderByCreatedDesc();

  @Query(value = FEATURED_QUERY, nativeQuery = true)
  List<Sketch> getMostLikedSketches(int count);


}
