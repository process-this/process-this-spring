package io.github.processthis.springserver.view;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatSketch {

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getName();

  URI getHref();


}
