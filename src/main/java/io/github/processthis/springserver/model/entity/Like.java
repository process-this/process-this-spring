package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatLike;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Like implements FlatLike {


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

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonSerialize(contentAs = UserProfile.class)
  private List<UserProfile> userProfiles = new LinkedList<>();
  private List<Sketch> sketches = new LinkedList<>();
  //TODO verify if this is actually importing the foreign keys of Userprofiles and sketches.

  public Date getCreated() {
    return created;
  }

  public Date getUpdated() {
    return updated;
  }


}
