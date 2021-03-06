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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private OrdersRepository ordersRepository;

    @Autowired
    public Collection<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Collection<Orders> findAllOrdersByCustomer(Customer customer) {
        return ordersRepository.findAllByCustomer(customer);
    }

    @Override
    public Collection<Orders> findAllOrdersByStaffId(int id) {
        return ordersRepository.findAllByStaffId(id);
    }

    @Override
    public Optional<Orders> findByOrdersId(int id) {
        return ordersRepository.findOrdersById(id);
    }

//    @Override
//    public Optional<Orders> findByCustomer(Customer customer) {
//        return ordersRepository.findByCustomer(customer);
//    }

    @Transactional
    @Override
    public void save(Orders order){
        order.setDateOfOrder(LocalDateTime.now().toString());
        ordersRepository.save(order);
    }

    @Override
    public void removeOrder(int id) {
        ordersRepository.removeOrdersById(id);
    }
}
