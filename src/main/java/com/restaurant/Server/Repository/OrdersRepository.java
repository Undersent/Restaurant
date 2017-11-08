package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    //Collection<Orders> findAllByStaff(Staff staff);
    Collection<Orders> findAllByCustomer(Customer customer);
    Collection<Orders> findAllByStaffId(int id);
    Optional<Orders> findOrdersById(int id);
    void removeOrdersById(int id);
    //Optional<Orders> findByCustomer(Customer customer);
    //Collection<Orders> findAllByCustomerId(int id);
}
