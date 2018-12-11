package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"movies"})
@Entity
public class Actor {

    private Integer actorId;
    private String name;
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>(0);
    private Integer movieOrderId;

    public Actor() {
    }

    public Actor(String name, Integer movieOrderId) {
        this.name = name;
        this.movieOrderId = movieOrderId;
    }

    public Actor(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACTOR_ID", nullable = false, unique = true)
    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Column(name = "ACTOR_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Column(name = "MVE_ORD_ID")
    public Integer getMovieOrderId() {
        return movieOrderId;
    }

    public void setMovieOrderId(Integer movieOrderId) {
        this.movieOrderId = movieOrderId;
    }
}
