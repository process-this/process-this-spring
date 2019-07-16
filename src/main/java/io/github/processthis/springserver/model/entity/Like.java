package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
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
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Like {

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
  private UserProfile userProfile ;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sketch_id", nullable = false)
  @JsonSerialize(as = FlatSketch.class)
  private Sketch sketch;


  public UUID getId() {
    return id;
  }

  public Date getCreated() {
    return created;
  }


  public UserProfile getUserProfile() {
    return userProfile;
  }

  public void setUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
  }

  public Sketch getSketch() {
    return sketch;
  }

  public void setSketch(Sketch sketch) {
    this.sketch = sketch;
  }
}


// use .size to check how many likes are there

// sketches/ {id}/like/{user_id}   sketches and its id to see the likes by user
//users/{id}/like/{sketch_id} users and their id to see the sketches they have like'd
//will use sketch controller and user controller to delete likes

