package com.restaurant.Server.controllers.order;

import com.restaurant.Server.Logic.Assigner;
import com.restaurant.Server.Service.CustomerService;
import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Meal;
import com.restaurant.Server.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("add/order")
public class AddOrderController {

    OrdersService ordersService;
    CustomerService customerService;
    MealService mealService;
    Assigner assigner;

    @RequestMapping("/{customerId}/{mealId}")
    @PostMapping
    public ResponseEntity<?> addOrderForCustomer(@PathVariable("customerId") int customerId,
                                                 @PathVariable("mealId") int mealId,
                                                 @RequestBody(required = false) String description){

        Customer customer = isCustomerEmpty(customerService
                .findById(customerId).get());

        Meal meal = isMealEmpty(mealService
                .findByMealId(mealId).get());

        Orders order = Orders.builder()
                .customer(customer)
                .meal(meal)
                .otherDetails(description)
                .build();

        ordersService.save(order);

        assigner.assignOrder(customer, order);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);//TODO
    }

    //  TODO
    private Meal isMealEmpty(Meal m) {
        return Optional.ofNullable(m)
                .filter(meal -> meal != null )
                .orElseThrow(IllegalArgumentException::new);
    }

    //  TODO
    private Customer isCustomerEmpty(Customer c) {
        return Optional.ofNullable(c)
                .filter(cus -> cus != null )
                .orElseThrow(IllegalArgumentException::new);
    }

}
