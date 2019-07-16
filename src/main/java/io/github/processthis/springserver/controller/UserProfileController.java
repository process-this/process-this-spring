package io.github.processthis.springserver.controller;

import io.github.processthis.springserver.model.dao.LikeRepository;
import io.github.processthis.springserver.model.dao.SketchRepository;
import io.github.processthis.springserver.model.dao.UserProfileRepository;
import io.github.processthis.springserver.model.entity.Like;
import io.github.processthis.springserver.model.entity.Sketch;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.apache.catalina.User;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Sketch.class)
@RequestMapping("sketches")
public class UserProfileController {


  private final UserProfileRepository userProfileRepository;
  private final SketchRepository sketchRepository;
  private final LikeRepository likeRepository;


  public UserProfileController(
      SketchRepository repository,
      UserProfileRepository userProfileRepository,
      LikeRepository likeRepository) {
    this.sketchRepository = repository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserProfile> list(@RequestParam(value = "sketch", required = false) UUID sketchId) {
    if (sketchId == null ) {
      return userProfileRepository.getAllByOrderByUsernameAsc();
    } else {
      Sketch sketch = sketchRepository.findById(sketchId).get();
      return userProfileRepository.getAllBySketchOrderById(sketch);
    }
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> getLikes() {return likeRepository.getAllByOrderByCreated();}

  @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<UserProfile> search(@RequestParam(value = "q", required = true)String profileFragment){
    return userProfileRepository.getAllByUsernameContainsOrderByUsernameAsc(profileFragment);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserProfile> post(@RequestBody UserProfile userProfile) {
    userProfileRepository.save(userProfile);
    if(userProfile.getSketch() != null) {
      UUID genreId = userProfile.getSketch().getId();
      userProfile.setGenre(sketchRepository.findById(genreId).get());
    }
    return ResponseEntity.created(userProfile.getHref()).body(userProfile);
  }


  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserProfile get(@PathVariable("id") UUID id) {
    return userProfileRepository.findById(id).get();
  }


  //404 Return
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound(){
  }
}
