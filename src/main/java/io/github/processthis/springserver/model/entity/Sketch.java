package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.processthis.springserver.view.FlatSketch;
import io.github.processthis.springserver.view.FlatUserProfile;
import io.github.processthis.springserver.view.LikeUserProfile;
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
 * * This class implements FlatSketchr. it defines the attributes of a userProfile object and *
 * creates setters and getters for those fields
 */
@Entity
@Component
@JsonIgnoreProperties(value = {"created", "updated",
    "href"}, allowGetters = true, ignoreUnknown = true)
public class Sketch implements FlatSketch {

    private static EntityLinks entityLinks;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "sketch_id", columnDefinition = "CHAR(16) FOR BIT DATA",
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
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String sketchDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_profile")
    @JsonSerialize(as = FlatUserProfile.class)
    private UserProfile userProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sketch", cascade = {CascadeType.DETACH,
        CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonSerialize(contentAs = LikeUserProfile.class)
    private List<Like> likes = new LinkedList<>();

    @Column(length = 4000)
    private String code;


    /**
     * Gets the code comprising a sketch as a String literal
     */
    public String getCode() {
        return code;
    }

    /**
     * sets the code comprising a sketch as a string literal
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * gets the userProfile who created a sketch
     */
    public UserProfile getUserProfile() {
        return userProfile;
    }


    /**
     * sets the userprofile who created a sketch
     */
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * Gets the UUID of a sketch
     */
    @Override
    public UUID getId() {
        return id;
    }

    /**
     * Gets the date a sketch was created a Date
     */
    @Override
    public Date getCreated() {
        return created;
    }

    /**
     * Gets the date a sketch was updated as Date
     */
    @Override
    public Date getUpdated() {
        return updated;
    }

    /**
     * Gets the name of a sketch as a String
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of sketch
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the String description of a Sketch
     */
    @Override
    public String getSketchDescription() {
        return sketchDescription;
    }

    public void setSketchDescription(String sketchDescription) {
        this.sketchDescription = sketchDescription;
    }

    /**
     * Gets a list of likes associated with a sketch
     */
    public List<Like> getLikes() {
        return likes;
    }

    public URI getHref() {
        return entityLinks.linkForSingleResource(UserProfile.class, getUserProfile().getId())
            .slash("sketches").slash(id).toUri();
    }


    /**
     * Required method for Spring entity that initializes sketch as a SpringBean
     */
    @PostConstruct
    private void init() {
        String ignore = entityLinks.toString();
    }

    /**
     * * Required method for Spring entity that initializes sketch as a SpringBean
     */
    @Autowired
    private void setEntityLinks(EntityLinks entityLinks) {
        Sketch.entityLinks = entityLinks;
    }


}

