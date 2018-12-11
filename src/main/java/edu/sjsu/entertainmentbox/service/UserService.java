package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.LoginComponent;
import edu.sjsu.entertainmentbox.dao.CustomerRepository;
import edu.sjsu.entertainmentbox.dao.UserRepository;
import edu.sjsu.entertainmentbox.model.Customer;
import edu.sjsu.entertainmentbox.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoginComponent loginComponent;

    public Iterable<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User addUser(User user) {
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public  void deleteUser( String username){
        userRepository.deleteById(username);
    }

    public User getUser(String username){
        Optional<User> user = userRepository.findById(username);

        if(!user.isPresent()){
            System.out.println("username Not Found-"+username);
        }

        return user.get();
    }


    public LoginComponent login(String emailAddress, String password) {

        Integer customerId = -1;

        User loggedInUser = userRepository.findByEmailAddressAndPassword(emailAddress, password);
        Optional<Customer> customer = customerRepository.findByEmailAddress(loggedInUser.getEmailAddress());

        if(customer.isPresent())
        {
            customerId = customer.get().getCustomerId();
        }

        loginComponent.setUser(loggedInUser);
        loginComponent.setCustomerId(customerId);


        return loginComponent;
    }

}
