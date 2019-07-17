package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.processthis.springserver.view.FlatUserProfile;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Entity
@Component
@JsonIgnoreProperties(value = {"id","created","updated","href"},
    allowGetters = true, ignoreUnknown = true)
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

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_profile_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID followId;

  @Column
  private String authId;

  @Column
  private String username;

  @Column
  private String bio;

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

  @Override
  public Sketch getSketches() {
    return null;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public UUID getFollowId() {
    return followId;
  }

  @Override
  public String getBio() {
    return bio;
  }

  public void setAuthId(String authId) {
    this.authId = authId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  @Override
  public URI getHref() {
    return entityLinks.linkForSingleResource(UserProfile.class, id).toUri();
  }

  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  @Autowired
  public void setEntityLinks(EntityLinks entityLinks) {
    UserProfile.entityLinks = entityLinks;
  }
}
