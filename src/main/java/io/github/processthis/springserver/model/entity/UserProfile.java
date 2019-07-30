package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@JsonIgnoreProperties(value = {"id","created","updated","href", "sketches", "likes"},
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



  @Column(nullable = false)
  private String authId;

  @Column(nullable = false)
  private String username;

  @Column(nullable = true)
  private String bio;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JsonSerialize(contentAs = FlatSketch.class)
  private List<Sketch> sketches = new LinkedList<>();


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = {CascadeType.DETACH,
      CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<Like> likes = new LinkedList<>();

  public List<Sketch> getSketches() {
    return sketches;
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

  @Override
  public String getUsername() {
    return username;
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

  public List<Like> getLikes() {
    return likes;
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
