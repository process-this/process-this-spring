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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(Sketch.class)
@RequestMapping("users/{userId}/sketches")
public class SketchController {

  private final SketchRepository repository;
  private final UserProfileRepository userProfileRepository;
  private final LikeRepository likeRepository;

  @Autowired
  public SketchController(
      SketchRepository repository,
      UserProfileRepository userProfileRepository,
      LikeRepository likeRepository) {
    this.repository = repository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }


  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> get() {
    return repository.getAllByOrderByNameAsc();
  }

  @GetMapping(value = "{sketchId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Sketch get(@PathVariable("userId") UUID userId, @PathVariable("sketchId") UUID sketchId) {
    Sketch sketch = repository.findById(sketchId).get();
    if (!sketch.getUserProfile().getId().equals(userId)) {
      throw new NoSuchElementException();
    }
    return sketch;
  }

  @GetMapping(value = "{sketchId}/likes/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> get(@PathVariable("userId") UUID userId, @PathVariable("sketchId") UUID sketchId, @PathVariable UUID likeId) {
    Sketch sketch = repository.findById(sketchId).get();
    UserProfile userProfile = userProfileRepository.findById(userId).get();
      return likeRepository.getAllByUserProfileOrderByCreatedAsc(userProfile);
    }


  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Sketch> attach(@PathVariable("userId") UUID userId, @RequestBody Sketch sketch) {
    UserProfile userProfile = userProfileRepository.findById(userId).get();
    sketch.setUserProfile(userProfile);
    repository.save(sketch);
    userProfileRepository.save(userProfile);
    return ResponseEntity.created(sketch.getHref()).body(sketch);
  }

  @PutMapping(value = "{sketchId}/like", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Like> attach(@PathVariable("userId") UUID userId, @PathVariable("sketchId") UUID sketchId, @RequestBody Like like) {
    UserProfile userProfile = userProfileRepository.findById(userId).get();
    Sketch sketch = repository.findById(sketchId).get();
    like.setSketch(sketch);
    likeRepository.save(like);
    repository.save(sketch);
    return ResponseEntity.created(like.getHref()).body(like);
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound(){}
}

   // @Transactional
   // @DeleteMapping(value = "{id}")
   // @ResponseStatus(HttpStatus.NO_CONTENT)
   // public void delete(@PathVariable("id") UUID id) {
   //   Sketch sketch = get(id);
   //   List<Like> like = sketch.getLikes();
//    for (Movie movie : genre.getMovies()){
//      movie.setGenre(null);
//    }

    //  userProfileRepository.saveAll(userProfiles);
    //  repository.delete(sketch);





