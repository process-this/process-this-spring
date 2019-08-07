package io.github.processthis.springserver.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.dao.LikeRepository;
import io.github.processthis.springserver.model.dao.SketchRepository;
import io.github.processthis.springserver.model.dao.UserProfileRepository;
import io.github.processthis.springserver.model.entity.Like;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import io.github.processthis.springserver.view.FlatUserProfile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains methods primarily accessing the userProfile entity and likes
 */
@RestController
@RequestMapping("users")
@ExposesResourceFor(UserProfile.class)
public class UserProfileController {

  private final UserProfileRepository userProfileRepository;
  private final SketchRepository sketchRepository;
  private final LikeRepository likeRepository;


  /**
   * THis constructor creates an instance of UserProfileController class using the fields listed
   * below
   */
  public UserProfileController(
      UserProfileRepository userProfileRepository,
      SketchRepository sketchRepository,
      LikeRepository likeRepository) {
    this.sketchRepository = sketchRepository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }

  /**
   * This method returns a list of all userProfile objects ordered by name
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonSerialize(contentAs = FlatUserProfile.class)
  public List<UserProfile> userList() {
    return userProfileRepository.getAllByOrderByUsernameAsc();
  }

  /**
   * This method queries and returns one specific user profile by UUID
   *
   * @param id the userProfile UUID
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserProfile get(@PathVariable("id") UUID id) {
    UserProfile userProfile = userProfileRepository.findById(id).get();
    return userProfile;
  }

  /**
   * This method queries and returns a list of all user profiles whose name contains the string
   * fragment supplied as a parameter
   *
   * @param profileFragment thr string used as the search parameter
   */
  @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserProfile> search(
      @RequestParam(value = "q", required = true) String profileFragment) {
    return userProfileRepository.getAllByUsernameContainsOrderByUsernameAsc(profileFragment);
  }

  /**
   * This method will save changes to or create a new user profile
   *
   * @param userProfile attributes of the userProfile object being created, both name and authId are
   * required nonnull values
   * @return the new user profile
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserProfile> userPost(@RequestBody UserProfile userProfile) {
    userProfileRepository.save(userProfile);
    return ResponseEntity.created(userProfile.getHref()).body(userProfile);
  }

  /**
   * returns a list ordered by date created of all likes created by a user with a given userId
   */
  @GetMapping(value = "{id}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> getLikes(@PathVariable("id") UUID id) {
    UserProfile userProfile = userProfileRepository.findById(id).get();
    return likeRepository.getAllByUserProfileOrderByCreatedAsc(userProfile);
  }

  /**
   * returns a list of all sketches created by a user with a given Id
   */
  @GetMapping(value = "{id}/sketches", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> getSketches(@PathVariable("id") UUID id) {
    UserProfile sketch = userProfileRepository.findById(id).get();
    return sketchRepository.getAllByUserProfile(sketch);
  }

  /**
   * Creates a like between a user of a given Id to a sketch of a given Id
   *
   * @param userId user "liking" a sketch
   * @param sketchId sketch being liked
   * @return user profile with new like object attached
   */
  @PutMapping(value = "{userId}/likes/{sketchId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserProfile like(@PathVariable("userId") UUID userId,
      @PathVariable("sketchId") UUID sketchId) {
    UserProfile userProfile = get(userId);
    Sketch sketch = sketchRepository.findById(sketchId).get();
    boolean alreadyLikes = false;
    for (Like like : userProfile.getLikes()) {
      if (like.getSketch().getId().equals(sketchId)) {
        alreadyLikes = true;
        break;
      }
    }
    if (!alreadyLikes) {
      Like like = new Like();
      like.setSketch(sketch);
      like.setUserProfile(userProfile);
      likeRepository.save(like);
    }
    return userProfile;
  }

  /**
   * This method deletes a like object that had been created between a user with a given Id and a
   * sketch with a given Id
   *
   * @param userId user who used to like a sketch
   * @param sketchId the sketch that's feeling no love
   */
  @DeleteMapping(value = "{userId}/likes/{sketchId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void unlike(@PathVariable("id") UUID userId, @PathVariable("id") UUID sketchId) {
    UserProfile userProfile = get(userId);
    Sketch sketch = sketchRepository.findById(sketchId).get();
    for (Like like : userProfile.getLikes()) {
      if (like.getSketch().getId().equals(sketchId)) {
        likeRepository.delete(like);
        break;
      }
    }
  }

  /**
   * This method says mean things about you when you go to sleep at night
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }
}
