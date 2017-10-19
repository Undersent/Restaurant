package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByStaff(Staff staff);
    List<Orders> findAllByCustomer(Customer customer);
    Orders findByCustomer(Customer customer);
}
