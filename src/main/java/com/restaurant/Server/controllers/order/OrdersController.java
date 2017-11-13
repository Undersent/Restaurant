package com.restaurant.Server.controllers.order;

import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("/order")
public class OrdersController {

    OrdersService ordersService;

    @GetMapping(value={"/staff/{id}"})
    Collection<Orders> getOrderByCustomerId(@PathVariable("id") int id ){
        return ordersService.findAllOrdersByStaffId(id);
    }
}
