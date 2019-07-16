package io.github.processthis.springserver.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.model.dao.UserProfileRepository;
import io.github.processthis.springserver.model.entity.UserProfile;
import io.github.processthis.springserver.view.FlatUserProfile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user_profile")
@ExposesResourceFor(UserProfile.class)
public class UserProfileController {

  private final UserProfileRepository userProfileRepository;

  public UserProfileController(
      UserProfileRepository userProfileRepository) {
    this.userProfileRepository = userProfileRepository;
  }



  // Get Mappings.
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
