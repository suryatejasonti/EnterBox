package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.MoviePlayLogComponent;
import edu.sjsu.entertainmentbox.dao.CustomerRepository;
import edu.sjsu.entertainmentbox.dao.MoviePlayLogRepository;
import edu.sjsu.entertainmentbox.dao.MovieRepository;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    MoviePlayLogRepository moviePlayLogRepository;


    @Override
    public void addMovie(Movie movie) {


    }

    @Override
    public void editMovie(Movie movie) {

        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> searchMovies() {

        return movieRepository.findAll();
    }

    @Override
    public List<Customer> browseCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Optional<List<Customer>> customers; //= customerRepository.findAllOrderByCustomerMoviesMoviePlayLogsMveStartTs();
        /*if(customers.isPresent())
        {
            customerList = customers.get();
        }
        else
        {
            customerList = new ArrayList<>();
        }
*/
        return customerList;
    }

    @Override
    public List<MoviePlayLogComponent> getMoviePlayhistory(Integer customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        List<MoviePlayLogComponent> moviePlayLogComponents = new ArrayList<MoviePlayLogComponent>();
        Set<Movie> movies;

        /*if(customer.isPresent())
        {
            movies = customer.get().getMovies();
            if(movies!=null)
            {
                for (Movie movie: movies) {

                    moviePlayLogComponents.add(new MoviePlayLogComponent(movie, movie.getMoviePlayLogs()));
                }
            }
            else
            {
                //********** Movies does not exist ***********
            }
        }
        else
        {
            //*********** Customer does not exist ***********
        }*/

        return moviePlayLogComponents;
    }

    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    @Override
    public List<Customer> getTopNCustomers(Integer timePeriod) {

        return null;
    }

    //**For every movie, it can be counted as only one play for the same customer within 24 hours.
    //timePeriod should be 1, 7 or 30
    @Override
    public Integer getNumberOfPlays(Integer timePeriod, Integer movieId) {

        Integer noOfPlays = 0;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -timePeriod);
        System.out.println("Date = "+ cal.getTime());

    /* Optional<List<MoviePlayLog>>  moviePlayLogs = moviePlayLogRepository.noOfPlaysForAMovie(cal.getTime(),movieId);

      if(moviePlayLogs.isPresent())
      {
          noOfPlays = moviePlayLogs.get().size();
      }*/

        return  noOfPlays;
    }

    @Override
    public List<Customer> getTopNMovies(Integer timePeriod) {
        return null;
    }
}
