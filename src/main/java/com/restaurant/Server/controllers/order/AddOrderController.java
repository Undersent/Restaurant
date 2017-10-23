package com.restaurant.Server.controllers.order;

import com.restaurant.Server.Service.CustomerService;
import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.model.Meal;
import com.restaurant.Server.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("add/order")
public class AddOrderController {

    OrdersService ordersService;
    CustomerService customerService;
    MealService mealService;

    @RequestMapping("/{customerId}/{mealId}")
    @PostMapping
    public ResponseEntity<?> addOrderForCustomer(@PathVariable("customerId") int customerId,
                                                 @PathVariable("mealId") int mealId){
        //dodac logike dla przyporzadkowywania mmeala odpowiedniemu staffowi itp
        ordersService.save(Orders.builder()
                .customer(customerService
                        .findById(customerId)
                        .get())
                .meal(mealService
                        .findByMealId(mealId)
                        .get())
                .build());

        return ResponseEntity.ok(HttpStatus.ACCEPTED);//TODO
    }

}
