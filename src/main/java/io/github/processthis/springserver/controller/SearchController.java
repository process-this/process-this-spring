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

@RestController
@RequestMapping("search")
public class SearchController {

    private final SketchRepository sketchRepository;
    private final UserProfileRepository userProfileRepository;
    private final LikeRepository likeRepository;



  @Autowired
  public SearchController(
      SketchRepository repository,
      UserProfileRepository userProfileRepository,
      LikeRepository likeRepository) {
    this.sketchRepository = repository;
    this.userProfileRepository = userProfileRepository;
    this.likeRepository = likeRepository;
  }

  @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> search(@RequestParam("q") String fragment) {
    return sketchRepository.findAllByNameContainingOrderByNameAsc(fragment);
  }

  @GetMapping(value = "feed", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> get(@RequestParam(value = "count", required = false, defaultValue = "10") int count) {
    return sketchRepository.getMostLikedSketches(count);
  }

  @GetMapping(value = "home", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Sketch> getHome(int count) {
    return sketchRepository.getTopByOrderByCreatedDesc(count);
  }

}
