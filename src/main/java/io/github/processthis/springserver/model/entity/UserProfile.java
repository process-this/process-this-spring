package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.jndi.toolkit.url.Uri;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @Column
  private String authId;

  @Column
  private String username;

//  @Id
//  @GeneratedValue(generator = "uuid2")
//  @GenericGenerator(name = "uuid2", strategy = "uuid2")
//  @Column(name = "user_profile_id", columnDefinition = "CHAR(16) FOR BIT DATA",
//      nullable = false, updatable = false)
//  private UUID followId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "sketch_id")
  private Sketch sketch;

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

//  @Override
//  public UUID getFollowId() {
//    return followId;
//  }

  @Override
  public Sketch getSketch() {
    return sketch;
  }

  public void setGenre(Sketch sketch) {
    this.sketch = sketch;
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
  public static void setEntityLinks(EntityLinks entityLinks) {
    UserProfile.entityLinks = entityLinks;
  }
}
