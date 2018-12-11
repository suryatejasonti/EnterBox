package edu.sjsu.entertainmentbox.dao;

import edu.sjsu.entertainmentbox.component.CustomerSubscriptionComponent;
import edu.sjsu.entertainmentbox.model.CustomerSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerSubscriptionRepository extends JpaRepository<CustomerSubscription, Integer> {

        @Query("SELECT" +
                " new edu.sjsu.entertainmentbox.component.CustomerSubscriptionComponent(month(subscriptionStartDate), year(subscriptionStartDate), customer)" +
                " FROM" +
                " CustomerSubscription cs" +
                " where" +
                " subscriptionType = :subscriptionType" +
                " group by" +
                " cs.customer.customerId" +
                " order by" +
                " month(subscriptionStartDate) desc")
        List<CustomerSubscriptionComponent> findUniqueSubscriptionUsers(@Param("subscriptionType") String subscriptionType);


        List<CustomerSubscription> findByCustomerCustomerIdAndSubscriptionStatus(@Param("CustomerId") Integer CustomerId, @Param("subscriptionStatus") String subscriptionStatus);
}
