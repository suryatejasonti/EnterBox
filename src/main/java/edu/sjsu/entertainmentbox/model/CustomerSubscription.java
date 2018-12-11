package edu.sjsu.entertainmentbox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CustomerSubscription {

    private Integer subscriptionId;
    private Customer customer;
    private String subscriptionType;
    private String subscriptionStatus;
    private Integer price;
    private Date subscriptionTS;
    private Date subscriptionStartDate;
    private Date subscriptionEndDate;
    private Movie movie;
   // private Transaction transaction;
    private Integer subscriptionDuration;

    public CustomerSubscription() {
    }

    public CustomerSubscription(String subscriptionType, String subscriptionStatus, Integer price, Date subscriptionTS, Date subscriptionStartDate, Date subscriptionEndDate) {
        this.subscriptionType = subscriptionType;
        this.subscriptionStatus = subscriptionStatus;
        this.price = price;
        this.subscriptionTS = subscriptionTS;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUST_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column(name = "SUBSCRPTN_TYPE", nullable = false)
    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Column(name = "PRICE")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SUBSCRPTN_TS",  nullable = false, length = 10)
    public Date getSubscriptionTS() {
        return subscriptionTS;
    }

    public void setSubscriptionTS(Date subscriptionTS) {
        this.subscriptionTS = subscriptionTS;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SUBSCRPTN_START", nullable = false, length = 10)
    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "SUBSCRPTN_END", nullable = false, length = 10)
    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    @Column(name = "SUBSCRPTN_STATUS", nullable = false)
    public String getSubscriptionStatus() {
        return subscriptionStatus;
    }

    public void setSubscriptionStatus(String subscriptionStatus) {
        this.subscriptionStatus = subscriptionStatus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    @JsonIgnore
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /*@OneToOne(fetch = FetchType.LAZY, mappedBy = "subscription", cascade = CascadeType.ALL)
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }*/

    @Column(name = "SUBSCRPTN_DURATION")
    public Integer getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(Integer subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    }
}
