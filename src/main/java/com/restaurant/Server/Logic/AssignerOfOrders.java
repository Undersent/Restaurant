package com.restaurant.Server.Logic;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.Service.CounterService;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;


@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class AssignerOfOrders implements Assigner{

    private CounterService counterService;

    @Override
    public void assignOrder(Customer customer, Orders order) {
        counterService.findAll()
                .stream()
                .min(Comparator.comparingInt(Counter::getCount))
                .ifPresent(c -> AddOrdersToStaff(c, order));
    }

    private void AddOrdersToStaff(Counter c, Orders order) {
        order.setStaffId(c.getStaff().getStaffId());
        saveUpdatedCounterToRepository(c.getStaff(), c);
    }

    private void saveUpdatedCounterToRepository(Staff staff, Counter c) {
        c.setCount(c.getCount()+1);
        c.setStaff(staff);
        counterService.save(c);
    }
}
