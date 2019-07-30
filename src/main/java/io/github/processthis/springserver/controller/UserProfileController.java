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

@RestController
@RequestMapping("users")
@ExposesResourceFor(UserProfile.class)
public class UserProfileController {

  private final UserProfileRepository userProfileRepository;
  private final SketchRepository sketchRepository;
  private final LikeRepository likeRepository;


  public UserProfileController(
      UserProfileRepository userProfileRepository,
      SketchRepository sketchRepository,
      LikeRepository likeRepository) {
    this.sketchRepository = sketchRepository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @JsonSerialize(contentAs = FlatUserProfile.class)
  public List<UserProfile> userList() {
    return userProfileRepository.getAllByOrderByUsernameAsc();
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserProfile get(@PathVariable("id") UUID id) {
    UserProfile userProfile = userProfileRepository.findById(id).get();
    return userProfile;
  }

  @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserProfile> search(@RequestParam(value = "q", required = true) String profileFragment){
    return userProfileRepository.getAllByUsernameContainsOrderByUsernameAsc(profileFragment);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserProfile> userPost(@RequestBody UserProfile userProfile) {
    userProfileRepository.save(userProfile);
    return ResponseEntity.created(userProfile.getHref()).body(userProfile);
  }

  @GetMapping(value = "{id}/likes", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> getLikes(@PathVariable("id") UUID id) {
    UserProfile userProfile = userProfileRepository.findById(id).get();
    return likeRepository.getAllByUserProfileOrderByCreatedAsc(userProfile);
  }

  @GetMapping(value = "{id}/sketches", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> getSketches(@PathVariable("id") UUID id) {
    UserProfile sketch = userProfileRepository.findById(id).get();
    return sketchRepository.getAllByUserProfile(sketch);
  }

  @PutMapping(value = "{userId}/likes/{sketchId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserProfile like(@PathVariable("userId") UUID userId, @PathVariable("sketchId") UUID sketchId) {
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
 //     userProfile.getLikes().add(like);
    }
    return userProfile;
  }

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

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound(){
  }
}
