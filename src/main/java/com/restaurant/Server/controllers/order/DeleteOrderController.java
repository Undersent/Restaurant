package com.restaurant.Server.controllers.order;

import com.restaurant.Server.Logic.Deleter;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.exceptions.MealNotFoundException;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("/delete/order")
public class DeleteOrderController {

    OrdersService ordersService;
    Deleter deleter;

    @DeleteMapping
    public ResponseEntity<?> deleteStaff(@RequestBody Orders order){

        Orders orderFound = ordersService.findByOrdersId(order.getId())
                .orElseThrow(() ->new MealNotFoundException(order.getId()));
        deleter.removeOrder(orderFound);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
