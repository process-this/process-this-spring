package io.github.processthis.springserver.controller;

import io.github.processthis.springserver.model.dao.LikeRepository;
import io.github.processthis.springserver.model.entity.Like;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public class LikeController {

  private final LikeRepository repository;

  @Autowired
  public LikeController(LikeRepository repository) {
    this.repository = repository;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Like> get() {
    return repository.getAllByOrderByCreated();
  }



//  TODO delete Like Controller

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(NoSuchElementException.class)
  public void notFound() {
  }

}



