package io.github.processthis.springserver.view;

import com.sun.jndi.toolkit.url.Uri;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

public interface FlatUserProfile {

  String getUsername();

  UUID getId();

  Date getCreated();

  Date getUpdated();

  String getAuthId();

  long getFollowId();

  Uri getProfileUrl();
}
