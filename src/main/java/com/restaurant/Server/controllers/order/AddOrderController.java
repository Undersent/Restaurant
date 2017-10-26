package com.restaurant.Server.controllers.order;

import com.restaurant.Server.Logic.Assigner;
import com.restaurant.Server.Service.CustomerService;
import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.exceptions.CustomerNotFoundException;
import com.restaurant.Server.exceptions.MealNotFoundException;
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

        validateCustomer(customerId);
        validateMeal( mealId);
        Orders order = Orders.builder()
                .customer(customerService
                        .findById(customerId).get())
                .meal(this.mealService.findByMealId(mealId).get())
                .otherDetails(description)
                .build();

        ordersService.save(order);

        assigner.assignOrder(customerService
                .findById(customerId).get(), order);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);//TODO
    }

    private void validateCustomer(int customerId) {
        this.customerService.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(customerId)
        );
    }

    private void validateMeal(int mealId) {
        this.mealService.findByMealId(mealId).orElseThrow(
                () -> new MealNotFoundException(mealId)
        );
    }


}
