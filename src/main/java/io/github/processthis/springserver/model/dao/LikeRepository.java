package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Like;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, UUID> {

  List<Like> getAllByOrderByCreated();

  List<Like> getAllByUserProfileOrderByCreatedAsc(UserProfile userProfile);

  List<Like> getAllBySketchOrderByCreatedAsc(Sketch sketch);

  Optional<Like> findLikeByUserProfileAndSketch(UserProfile userProfile, Sketch sketch);

  void deleteLikeByUserProfileAndSketch(UserProfile userProfile, Sketch sketch);

  void deleteLikesByUserProfile(UserProfile userProfile);

  void deleteLikesBySketch(Sketch sketch);


}
