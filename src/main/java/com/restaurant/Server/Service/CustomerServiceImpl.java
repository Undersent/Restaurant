package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.CustomerRepository;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Customer save(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }
}
