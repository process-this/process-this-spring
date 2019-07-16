package io.github.processthis.springserver.model.dao;

import io.github.processthis.springserver.model.entity.Like;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, Date> {

  List<Like> getAllByOrderByCreated();

//  List<UserProfile> getAllByLikeOrderByCreatedAsc(Like like);
//
//  List<Sketch> getAllByLikeOrderByUpdated(Like like);


}
