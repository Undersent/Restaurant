package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.OrdersRepository;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private OrdersRepository ordersRepository;

    @Override
    public List<Orders> findAllOrdersByStaff(Staff staff) {
        return ordersRepository.findAllByStaff(staff);
    }

    @Autowired
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> findAllOrdersByCustomer(Customer customer) {
        return ordersRepository.findAllByCustomer(customer);
    }

    @Override
    public Orders findByCustomer(Customer customer) {
        return ordersRepository.findByCustomer(customer);
    }

    @Transactional
    @Override
    public void save(Orders order){
        order.setDateOfOrder(LocalDateTime.now().toString());
        ordersRepository.save(order);
    }
}