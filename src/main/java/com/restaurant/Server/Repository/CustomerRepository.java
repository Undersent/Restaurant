package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(int id);
}
