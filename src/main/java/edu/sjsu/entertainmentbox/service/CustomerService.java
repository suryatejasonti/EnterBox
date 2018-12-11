package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.MoviesByRatingComponent;
import edu.sjsu.entertainmentbox.model.*;

import java.util.List;

public interface CustomerService {

    //The subscription can start at any day, and the subscription fee for the current month ends at 12 am the same day next month.
    // If next month does not have the same day, then it ends at the last day of next month.
    // For example, if you start your subscription on Jan 30 and only paid $10 monthly fee, the fee is good until the last day of February,
    // and you must pay/renew your subscription by 12 AM March 1st.
    CustomerSubscription startSubscription(Integer customerId, Integer noOfMonths, String subscriptionType, Integer price, Integer movieId);


    //View billing status: a subscription user must be able to find out when his subscription is up for renewal.
    List<String> viewBillingStatus(Integer customerId);

    //Filtering Features to be implemented in front end
    List<Movie> getAllMovies();

    //Call On Click of play - Set the return value i.e loginId to session to update EndTS appropriately
    Customer updateMovieStartStatus(Integer movieId, Integer customerId);

    //fetch the loginId from the session and update the stop TS
    MoviePlayLog updateMovieStopStatus(Integer logId, Integer customerId);

    //A customer can review a movie after he started playing a movie, no matter he finished playing or not.
    boolean checkPlayStatus(Integer logId);

    //Save review
    String saveReview(Integer movieId, Integer customerId, String review, Integer rsting);

    List<MoviesByRatingComponent> getTopNMoviesByRatings();

    List<Rating> getMovieReviews(Integer movieId);







}
