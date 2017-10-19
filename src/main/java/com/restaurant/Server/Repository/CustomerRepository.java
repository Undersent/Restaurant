package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Staff findByCustomerId(int id);
    //Staff findByLastName(String lastName);

}
