package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.CustomerRepository;
import com.restaurant.Server.model.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findByCustomerId(id);
    }

    @Transactional
    @Override
    public Customer save() {
        return customerRepository.save(Customer.builder()
                .build());
    }
}
