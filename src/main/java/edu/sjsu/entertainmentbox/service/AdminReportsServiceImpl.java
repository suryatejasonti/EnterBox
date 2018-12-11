package edu.sjsu.entertainmentbox.service;

import edu.sjsu.entertainmentbox.component.CustomerSubscriptionComponent;
import edu.sjsu.entertainmentbox.dao.CustomerSubscriptionRepository;
import edu.sjsu.entertainmentbox.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminReportsServiceImpl implements AdminReportsService {

    @Autowired
    CustomerSubscriptionRepository customerSubscriptionRepository;

    //month by month for the last 12 calendar months, including the current month
    @Override
    public Map<String, List<Customer>> getUniqueSubscriptionOrPayPerViewUsers(String subscriptionType) {
        Map<String, List<Customer>> uniqueSubscriptionMap = new HashMap<>();
        List<Customer> customers;
        String month;
        String year;
        String key;

        List<CustomerSubscriptionComponent> uniqueSubscriptionUsers = customerSubscriptionRepository.findUniqueSubscriptionUsers(subscriptionType);

        for (CustomerSubscriptionComponent component:uniqueSubscriptionUsers) {
            month = new DateFormatSymbols().getMonths()[component.getMonth()-1];
            year = component.getYear().toString();
            key = month + year;

            if(uniqueSubscriptionMap.containsKey(key))
            {
                uniqueSubscriptionMap.get(key).add(component.getCustomer());
            }
            else
            {
                customers = new ArrayList<>();
                customers.add(component.getCustomer());
                uniqueSubscriptionMap.put(key,customers);
            }
        }

        return uniqueSubscriptionMap;
    }

    // unique pay-per-view users (those who have played at least one Pay-Per-View movie,
    // either paid as non-subscriber, or paid f or PayPerViewOnly as a subscriber)
    //month by month for the last 12 calendar months, including the current month
    @Override
    public List<Customer> getUniquePayPerViewUsers() {
        return null;
    }

    //those who played at least one movie in the month
    //month by month for the last 12 calendar months, including the current month
    @Override
    public List<Customer> getUniqueActiveUsers() {
        return null;
    }

    //all registered users
    //month by month for the last 12 calendar months, including the current month
    @Override
    public List<Customer> getUniqueUsers() {
        return null;
    }

    //for the last 12 calendar months, including the current month
    @Override
    public List<Integer> getMontlySubscriptionIncome() {
        return null;
    }

    //for the last 12 calendar months, including the current month
    @Override
    public List<Integer> getMontlyPayPerViewIncome() {
        return null;
    }

    //for the last 12 calendar months, including the current month
    @Override
    public List<Integer> getMontlyTotalIncome() {
        return null;
    }
}
