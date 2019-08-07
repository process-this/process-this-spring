package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
import io.github.processthis.springserver.view.LikeSketch;
import io.github.processthis.springserver.view.LikeUserProfile;
import java.net.URI;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.EntityLinks;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * The class implements the FlatLike interface and defines the attributes of a Like Object as well
 * as setters and getters for those fields
 */
@Entity(name = "likes")
@Component
public class Like implements LikeSketch, LikeUserProfile {

  private static EntityLinks entityLinks;

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "like_id", columnDefinition = "CHAR(16) FOR BIT DATA",
      nullable = false, updatable = false)
  private UUID id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_profile_id", nullable = false)
  @JsonSerialize(as = FlatUserProfile.class)
  private UserProfile userProfile;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sketch_id", nullable = false)
  @JsonSerialize(as = FlatSketch.class)
  private Sketch sketch;


  /**
   * Gets the UUID of a Like object
   */
  public UUID getId() {
    return id;
  }

  /**
   * gets the date a Like was created as a Date
   */
  public Date getCreated() {
    return created;
  }


  /**
   * Gets the userProfile that created a like
   */
  public UserProfile getUserProfile() {
    return userProfile;
  }

  /**
   * sets the userprofile that created a like
   */
  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  /**
   * gets te sketched liked by a user
   */
  public Sketch getSketch() {
    return sketch;
  }

  /**
   * sets the sketch liked by a userProfile
   */
  public void setSketch(Sketch sketch) {
    this.sketch = sketch;
  }

}