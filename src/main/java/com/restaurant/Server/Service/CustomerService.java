package com.restaurant.Server.Service;

import com.restaurant.Server.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findById(int id);
    void save(int id);
}
