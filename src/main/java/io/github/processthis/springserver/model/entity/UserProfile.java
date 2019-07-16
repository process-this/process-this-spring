package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.jndi.toolkit.url.Uri;
import io.github.processthis.springserver.view.FlatUserProfile;

public class UserProfile implements FlatUserProfile {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_profile_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated;

  @NonNull
  private String authId;

  @NonNull
  private String Username;

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "follow_id", updatable = false, nullable = false)
  private long followId;

  @NonNull
  private Uri profileUrl;

  @Override
  public String getUsername() {
    return Username;
  }

  public void setUsername(String username) {
    Username = username;
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public Date getCreated() {
    return created;
  }

  @Override
  public Date getUpdated() {
    return updated;
  }

  @Override
  public String getAuthId() {
    return authId;
  }

  public void setAuthId(String authId) {
    this.authId = authId;
  }

  @Override
  public long getFollowId() {
    return followId;
  }

  @Override
  public Uri getProfileUrl() {
    return profileUrl;
  }

  @Autowired
  public static void setEntityLinks(EntityLinks entityLinks) {
    UserProfile.entityLinks = entityLinks;
  }
}
