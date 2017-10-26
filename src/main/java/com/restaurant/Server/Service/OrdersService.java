package com.restaurant.Server.Service;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface OrdersService {
    Collection<Orders> findAllOrdersByCustomer(Customer customer);
    Optional<Orders> findByCustomer(Customer customer);
    void save(Orders order);
}
