package io.github.processthis.springserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.processthis.springserver.view.FlatSketch;
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


@Entity
@Component
@JsonIgnoreProperties(value = {"created", "updated", "href"}, allowGetters = true, ignoreUnknown = true)
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

    @ManyToOne(fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_profile")
    private UserProfile userProfile;

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSketchDescription() {
        return sketchDescription;
    }

    public void setSketchDescription(String sketchDescription) {
        this.sketchDescription = sketchDescription;
    }

    public URI getHref(){
        return entityLinks.linkForSingleResource(Sketch.class, id).toUri();
    }

    @PostConstruct
    private void init(){
        String ignore = entityLinks.toString();
    }

    @Autowired
    private void setEntityLinks(EntityLinks entityLinks){
        Sketch.entityLinks = entityLinks;
    }


}

