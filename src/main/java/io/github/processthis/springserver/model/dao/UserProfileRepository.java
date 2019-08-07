package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, UUID> {

  /**
   * Returns a list of all userProfiles ordered by name
   */
  List<UserProfile> getAllByOrderByUsernameAsc();

  /**
   * Gets a list of all UserProfiles ordered by UUID
   */
  List<UserProfile> getAllByOrderById();

  /**
   * returns a ist of all user profiles whose name contains a given string fragment search term
   *
   * @param profileFragment string fragment search term
   */
  List<UserProfile> getAllByUsernameContainsOrderByUsernameAsc(String profileFragment);


}
