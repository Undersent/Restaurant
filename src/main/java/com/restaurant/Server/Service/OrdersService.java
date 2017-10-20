package com.restaurant.Server.Service;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface OrdersService {
    List<Orders> findAllOrdersByStaff(Staff staff);
    List<Orders> findAllOrdersByCustomer(Customer customer);
    Orders findByCustomer(Customer customer);
    void save(Orders order);
}
