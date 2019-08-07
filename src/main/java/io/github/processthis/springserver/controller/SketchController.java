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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class contains a myriad of methods that didn't actually end up in the the beta
 * release. But, they're really cool
 */
@RestController
@ExposesResourceFor(Sketch.class)
@RequestMapping("users/{userId}/sketches")
public class SketchController {

  private final SketchRepository repository;
  private final UserProfileRepository userProfileRepository;
  private final LikeRepository likeRepository;

  /**
   * This constructor creates an instance of the SketchController class from the three repository
   * parameters listed below.
   */
  @Autowired
  public SketchController(
      SketchRepository repository,
      UserProfileRepository userProfileRepository,
      LikeRepository likeRepository) {
    this.repository = repository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }


  /**
   * This method returns a list of all sketch objects ordered by name
   */
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

  /**
   * This method returns s a list of all users who have liked a sketch ordered by the date they
   * liked it
   *
   * @param userId the userId that created the sketch
   * @param sketchId the sketch of interest
   */
  @GetMapping(value = "{sketchId}/likes/", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> getLikes(@PathVariable("userId") UUID userId,
      @PathVariable("sketchId") UUID sketchId) {
    Sketch sketch = repository.findById(sketchId).get();
    UserProfile userProfile = userProfileRepository.findById(userId).get();
    return likeRepository.getAllByUserProfileOrderByCreatedAsc(userProfile);
  }


  /**
   * This method creates a sketch object tied to a user profile
   *
   * @param userId the user profile creating a sketch
   * @param sketch the sketch being created
   * @return a response entity of the sketch object
   */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Sketch> attach(@PathVariable("userId") UUID userId,
      @RequestBody Sketch sketch) {
    UserProfile userProfile = userProfileRepository.findById(userId).get();
    sketch.setUserProfile(userProfile);
    repository.save(sketch);
    userProfileRepository.save(userProfile);
    return ResponseEntity.created(sketch.getHref()).body(sketch);
  }


  /**
   * This method makes people sad. It's called when they search for a non-existent class
   */
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }


  /**
   * This method deletes a sketch object, any likes that had referenced it, and deletes its
   * reference in the user profile. It's kinda hawt.
   *
   * @param userId the userId whp created the sketch to be deleted
   * @param sketchId the sketch being deleted
   */
  @Transactional
  @DeleteMapping(value = "{sketchId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSketch(@PathVariable("userId") UUID userId,
      @PathVariable("sketchId") UUID sketchId) {
    Sketch sketch = get(userId, sketchId);
    UserProfile userProfile = userProfileRepository.findById(userId).get();
    List<Like> likes = sketch.getLikes();
    likes.forEach(likeRepository::delete);
    repository.delete(sketch);
    userProfileRepository.save(userProfile);
  }

}





