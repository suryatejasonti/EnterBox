package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {


    private Integer movieId;
    private String title;
    private String genre;
    private Integer year;
    private String studio;
    private String synopsis;
    private String Image;
    private String movieurl;
    private String MPAARating;
    private Set<Customer> customers = new HashSet<Customer>(0);
    private Set<CustomerSubscription> customerSubscriptions = new HashSet<CustomerSubscription>(0);
    private Set<Actor> actors = new HashSet<Actor>(0);
    private String directorName;
    private String country;
    private Set<Rating> ratings = new HashSet<>(0);
    private String movieType;
    private Integer price;

    public Movie() {
    }


    public Movie(String title, String genre, Integer year, String studio, String synopsis, String image, String movieurl, String MPAARating, Set<Actor> actors, String directorName, String country, String movieType, Integer price) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.studio = studio;
        this.synopsis = synopsis;
        this.Image = image;
        this.movieurl = movieurl;
        this.actors = actors;
        this.directorName = directorName;
        this.country = country;
        this.MPAARating = MPAARating;
        this.movieType = movieType;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MOVIE_ID", nullable = false, unique = true)
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    @Column(name = "TITLE", unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "GENRE")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "STUDIO")
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Column(name = "SYNOPSIS", length = 2000)
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    @Column(name = "IMAGE")
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Column(name="MOVIEURL")
    public String getMovieurl() {
        return movieurl;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }


    @Column(name = "DIRECTORNAME")
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @Column(name = "MOVIE_TYPE")
    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Column(name = "PRICE")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Movie_Actor", joinColumns = {
            @JoinColumn(name = "MOVIE_ID", nullable = false, updatable = false)},
    inverseJoinColumns = {@JoinColumn(name = "ACTOR_ID", nullable = false, updatable = false)})
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

    @Column(name = "MPAARATING")
    public String getMPAARating() {
        return MPAARating;
    }

    public void setMPAARating(String MPAARating) {
        this.MPAARating = MPAARating;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    public Set<CustomerSubscription> getCustomerSubscriptions() {
        return customerSubscriptions;
    }

    public void setCustomerSubscriptions(Set<CustomerSubscription> customerSubscriptions) {
        this.customerSubscriptions = customerSubscriptions;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "movies")
    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }


}
