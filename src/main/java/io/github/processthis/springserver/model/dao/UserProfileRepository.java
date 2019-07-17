package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {

  List<UserProfile> getAllByOrderByUsernameAsc();
  List<UserProfile> getAllByOrderById();
  List<UserProfile> getAllByUsernameContainsOrderByUsernameAsc(String profileFragment);


}
