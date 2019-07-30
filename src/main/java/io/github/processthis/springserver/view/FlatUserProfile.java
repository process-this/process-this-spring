package io.github.processthis.springserver.view;


import io.github.processthis.springserver.model.entity.Sketch;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface FlatUserProfile {

  String getUsername();

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getAuthId();



  String getBio();

  URI getHref();
}
