package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.MoviesByRatingComponent;
import edu.sjsu.entertainmentbox.dao.*;
import edu.sjsu.entertainmentbox.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerSubscriptionRepository customerSubscriptionRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    MoviePlayLogRepository moviePlayLogRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    MovieRepository movieRepository;



   /* @Autowired
    Transaction transaction;
    @Autowired
    CustomerSubscription customerSubscription;*/

    //The subscription can start at any day, and the subscription fee for the current month ends at 12 am the same day next month.
    // If next month does not have the same day, then it ends at the last day of next month.
    // For example, if you start your subscription on Jan 30 and only paid $10 monthly fee, the fee is good until the last day of February,
    // and you must pay/renew your subscription by 12 AM March 1st.

    //Pending -- 12am is not handled yet
    @Override
    public CustomerSubscription startSubscription(Integer customerId, Integer noOfMonths, String subscriptionType, Integer price, Integer movieId) {

        CustomerSubscription subscription = new CustomerSubscription();

        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, noOfMonths);
        Date subscriptionEndDate = cal.getTime();

        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Movie> movie = movieRepository.findById(movieId);
        CustomerSubscription customerSubscription = new CustomerSubscription(subscriptionType, "ACTIVE", noOfMonths*price, currentDate, currentDate,subscriptionEndDate);
        if(movie.isPresent())
        {
            customerSubscription.setMovie(movie.get());
        }
        if(customer.isPresent())
        {
            customerSubscription.setCustomer(customer.get());
            subscription = customerSubscriptionRepository.save(customerSubscription);

        }
        else
        {

        }

        return subscription;
    }



    //View billing status: a subscription user must be able to find out when his subscription is up for renewal.
    @Override
    public List<String> viewBillingStatus(Integer customerId) {

        String billingStatus = null;
        List<String> billingDetailsList= new ArrayList<>();

        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent())
        {
            List<CustomerSubscription> customerSubscriptions = customerSubscriptionRepository.findByCustomerCustomerIdAndSubscriptionStatus( customerId,"ACTIVE");
           /* Set<CustomerSubscription> subscriptions = customer.get().getSubscription();
            for (CustomerSubscription customerSubscription:subscriptions) {
                if(customerSubscription.getSubscriptionStatus().equalsIgnoreCase("SUBSCRIPTION"))
                {
                    billingStatus = customerSubscription.getSubscriptionEndDate().toString();
                }
            }*/
            billingDetailsList.add("SUBSCRIPTION TYPE ---------- SUBSCRIPTION EXPIRY");
            for (CustomerSubscription subscription:customerSubscriptions ) {
                billingDetailsList.add(subscription.getSubscriptionType()+" ---------- "+subscription.getSubscriptionEndDate());
            }
        }
        else
        {
            billingStatus = "Customer Not Registered";
        }
        return billingDetailsList;
    }

    //Filtering Features to be implemented in front end
    @Override
    public List<Movie> getAllMovies() {

        return null;
    }

    //Call On Click of play - Set the return value i.e logId to session to update EndTS appropriately
    @Override
    public Customer updateMovieStartStatus(Integer movieId, Integer customerId) {


        Optional<Customer> customer = customerRepository.findById(customerId);
        //System.out.println("Before updated customer!!"+customer.get().getMoviePlayLogs().size());

       // Set<MoviePlayLog> customerMoviePlayLogs;

        MoviePlayLog moviePlayLog = new MoviePlayLog("START", new Date(), null, movieId);
        //customerNew.setCustomerId(customerId);
        if(customer.isPresent())
        {
            System.out.println("customer found!!!!");
            moviePlayLog.setCustomer(customer.get());
        }

        /*System.out.println("updateMovieStartStatus!!!");
        if(customer.isPresent())
        {
            customerMoviePlayLogs = customer.get().getMoviePlayLogs();
            customerMoviePlayLogs.add(moviePlayLog);
            customer.get().setMoviePlayLogs(customerMoviePlayLogs);
        }*/


        moviePlayLogRepository.save(moviePlayLog);
        System.out.println("customerId::"+customerId);
        System.out.println(moviePlayLogRepository.findByCustomerCustomerId(customerId).size());
        /*System.out.println("customerId::"+customer.get().getCustomerId());
        Customer savedCustomer = customerRepository.save(customer.get());*/
        //moviePlayLogRepository.save(new MoviePlayLog(new Customer().setCustomerId(customerId), "START", movieId, new Date(), null ));


        return customer.get();
    }

    //fetch the loginId from the session and update the stop TS
    @Override
    public MoviePlayLog updateMovieStopStatus(Integer logId, Integer customerId) {
       // moviePlayLogRepository.save(new MoviePlayLog(logId, customerId,"END", movieId, new Date(), null ));
        MoviePlayLog moviePlayLog = new MoviePlayLog();

        if(customerRepository.existsById(customerId))
        {
            Optional<MoviePlayLog> log = moviePlayLogRepository.findById(logId);
            if(log.isPresent())
            {
                log.get().setMveEndTS(new Date());
                moviePlayLog = moviePlayLogRepository.save(log.get());
            }

        }
        return moviePlayLog;
    }

    //A customer can review a movie after he started playing a movie, no matter he finished playing or not.
    @Override
    public boolean checkPlayStatus(Integer logId) {
        boolean isStarted = false;
        Optional<MoviePlayLog> moviePlayLog = moviePlayLogRepository.findById(logId);
        if(moviePlayLog.isPresent())
        {
            isStarted = true;
        }

        return isStarted;
    }

    @Override
    public String saveReview(Integer movieId, Integer customerId, String review, Integer rating) {

        String saveStatus = "Successfully saved the review";
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        Rating rating1 = new Rating();
        rating1.setRating(rating);
        rating1.setRatingTS(new Date());
        rating1.setReview(review);
        if(movie.isPresent())
        {
            rating1.setMovie(movie.get());
           // ratingRepository.save(new Rating(customerId, movie.get(), rating, new Date(), review ));
        }
        else
        {
            saveStatus = "The movie selected is not found in the database";
        }

        if(customer.isPresent())
        {
            rating1.setCustomer(customer.get());
        }

        ratingRepository.save(rating1);

        return  saveStatus;

    }

    //Save review
    //Pending Movie names
    @Override
    public List<MoviesByRatingComponent> getTopNMoviesByRatings() {

        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);

        List<MoviesByRatingComponent> ratings = ratingRepository.findByRating(month-1);
        return ratings;
    }


    public Customer createCustomer(String emailId)
    {
        Customer customer = new Customer();
        customer.setEmailAddress(emailId);
       return customerRepository.save(customer);
    }

    @Override
    public List<Rating> getMovieReviews(Integer movieId)
    {
        List<Rating> ratingsList = new ArrayList<>();
        Optional<Movie> movie = movieRepository.findById(movieId);

        if(movie.isPresent())
        {
            Optional<List<Rating>> ratings = ratingRepository.findRatingByMovieMovieId(movieId);
            if(ratings.isPresent())
            {
                ratingsList = ratings.get();
            }
        }


        return ratingsList;
    }

}
