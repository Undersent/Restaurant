package com.restaurant.Server.controllers.customer;

import com.restaurant.Server.Service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("add/customer")
public class AddCustomerController {

    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addCustomer(){
        return new ResponseEntity<>(customerService.save()
                .getCustomerId(),HttpStatus.ACCEPTED);
    }

}
