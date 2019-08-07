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
import io.github.processthis.springserver.model.entity.Sketch;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller class for queries providing information to the home and featured views of
 * the android app.
 */
@RestController
@RequestMapping("search")
public class SearchController {

  private final SketchRepository sketchRepository;
  private final UserProfileRepository userProfileRepository;
  private final LikeRepository likeRepository;


  /**
   * This constructor is used to create an instance of the SearchController class using the three
   * parameters listed below.
   */
  @Autowired
  public SearchController(
      SketchRepository repository,
      UserProfileRepository userProfileRepository,
      LikeRepository likeRepository) {
    this.sketchRepository = repository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }

  /**
   * This method searches for all Sketches containing a given String in their name attribute and
   * returns them as a list
   *
   * @param fragment One or more characters of a search term
   * @return A list of Sketch objects
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> search(@RequestParam("q") String fragment) {
    return sketchRepository.findAllByNameContainingOrderByNameAsc(fragment);
  }

  /**
   * This method searches for a returns a list of sketch objects ordered by the most Likes in
   * descending order
   *
   * @param count is the number of sketches to return, with 10 being default
   */
  @GetMapping(value = "feed", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> get(
      @RequestParam(value = "count", required = false, defaultValue = "10") int count) {
    return sketchRepository.getMostLikedSketches(count);
  }

  /**
   * This method searches for and returns the 10 most recently created sketch objects in descending
   * order
   */
  @GetMapping(value = "home", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> getHome() {
    return sketchRepository.getTop10ByOrderByCreatedDesc();
  }

}
