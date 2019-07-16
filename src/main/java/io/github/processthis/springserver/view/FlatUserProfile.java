package io.github.processthis.springserver.view;

import com.sun.jndi.toolkit.url.Uri;
import io.github.processthis.springserver.model.entity.Sketch;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatUserProfile {

  String getUsername();

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getAuthId();

 // UUID getFollowId();

  Sketch getSketch();

  URI getHref();
}
