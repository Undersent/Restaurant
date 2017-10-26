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

    private CounterRepository counterRepository;
    CounterService counterService;

    @Override
    public void assignOrder(Customer customer, Orders order) {
        counterService.findAll()
                .stream()
                .min(Comparator.comparingInt(Counter::getCount))
                .ifPresent(c -> AddOrdersToStaff(c, order));
    }

    private void AddOrdersToStaff(Counter c, Orders order) {
        order.setStaffId(c.getStaff().getStaffId());
        saveCounterToRepository(c.getStaff(), c); //poprawic, najpierw dostac countera  po id staffa a potem zapisywac
    }

    private void saveCounterToRepository(Staff staff, Counter c) {
        counterRepository.save(Counter
                .builder()
                .staff(staff)
                .count(c.getCount()+1)
                .build());
    }
}
