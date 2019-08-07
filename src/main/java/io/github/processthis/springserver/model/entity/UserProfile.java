/*  Copyright [2019] [Asher Bearce, Jeffery Franken, Matthew Jones, Jennifer Nevares-Diaz]
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
   limitations under the License.
*/

package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
import io.github.processthis.springserver.view.LikeSketch;
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


/**
 * This class implements FlatUserProfile. it defines the attributes of a userProfile object and
 * creates setters and getters for those fields
 */
@Entity
@Component
//@JsonIgnoreProperties(value = {"id", "created", "updated", "href", "sketches", "likes"},
//    allowGetters = true, ignoreUnknown = true)
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
  @JsonSerialize(contentAs = LikeSketch.class)
  private List<Like> likes = new LinkedList<>();

  /**
   * Gets a list of all sketches made by a user
   */
  public List<Sketch> getSketches() {
    return sketches;
  }

  /**
   * Gets the user's UUID
   */
  @Override
  public UUID getId() {
    return id;
  }

  /**
   * Gets the date the UserProfile was created
   */
  @Override
  public Date getCreated() {
    return created;
  }

  /**
   * Gets the date user profile was updated
   */
  @Override
  public Date getUpdated() {
    return updated;
  }

  /**
   * Gets the authId
   */
  @Override
  public String getAuthId() {
    return authId;
  }

  /**
   * Gets the username
   */
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Gets the userProfile bio
   */
  @Override
  public String getBio() {
    return bio;
  }

  /**
   * sets the authId
   */
  public void setAuthId(String authId) {
    this.authId = authId;
  }

  /**
   * sets the userName
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Sets the user bio
   */
  public void setBio(String bio) {
    this.bio = bio;
  }

  /**
   * Get a list of all likes a user has made
   */
  public List<Like> getLikes() {
    return likes;
  }

  /**
   * Gets the URI path to the userProfile
   */
  @Override
  public URI getHref() {
    return entityLinks.linkForSingleResource(UserProfile.class, id).toUri();
  }

  /**
   * Required method for Spring entity that initializes UserProfile as a SpringBean
   */
  @PostConstruct
  private void init() {
    String ignore = entityLinks.toString();
  }

  /**
   * Sets the enititylinks Spring field
   */
  @Autowired
  public void setEntityLinks(EntityLinks entityLinks) {
    UserProfile.entityLinks = entityLinks;
  }
}
