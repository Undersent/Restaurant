package com.restaurant.Server.Logic;

import com.restaurant.Server.Service.CounterService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class DeleterOfOrders implements Deleter {

    private OrdersService ordersService;
    private CounterService counterService;

    @Override
    public void removeOrder(Orders order) {
        int staffId = order.getStaffId();
        ordersService.removeOrder(order.getId());

        counterService
                .findAll()
                .stream()
                .filter(c -> c.getStaff().getStaffId() == staffId)
                .findFirst()
                .ifPresent(this::decreaseCountForStaff);
    }

    private void decreaseCountForStaff(Counter counter) {
        counter.setCount(counter.getCount() - 1);
        counterService.save(counter);
    }
}
