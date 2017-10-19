package com.restaurant.Server.controllers;

import com.restaurant.Server.Service.CustomerService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class TestControllers {

    private final OrdersService ordersService;
    private final CustomerService customerService;
    private final StaffService staffService;

    @RequestMapping("/ordersave")
    public void saveOrder(){
        Customer customer = Customer.builder().build();
        Orders orders = Orders
                .builder()
                .otherDetails("syf")
                .build();
        orders.setCustomer(customer);
        customer.setOrder(Stream.of(orders)
                .collect(Collectors.toCollection(ArrayList::new)));
       // return ordersService.save(orders);
        customerService.save(customer);
    }

    @RequestMapping("/savestaff")
    public void saveStaff(){
        Staff staff = Staff.builder().firstName("admin").lastName("admin").build();
        staffService.saveStaff(staff, "admin");

    }


}
