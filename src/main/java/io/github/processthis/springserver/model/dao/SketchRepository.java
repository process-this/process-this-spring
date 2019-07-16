package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Sketch;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SketchRepository extends CrudRepository {

  List<Sketch> getAllByOrderByNameAsc();

}
