package com.restaurant.Server.controllers.admin;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.event.OnRegistrationCompleteEvent;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping(value = "admin/registration")
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RestController
public class RegisterController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ApplicationEventPublisher eventPublisher;
    private StaffService staffService;
    private CounterRepository counterRepository;

    @PostMapping
    @RequestMapping(value = "/waiter")
    public ResponseEntity<?> registerWaiterAccount(
            @RequestBody @Valid Staff account, HttpServletRequest request){

        LOGGER.debug("Registering waiter account with information {}", account);
        Staff registered = staffService.saveStaff(account, "ROLE_STAFF");
        counterRepository.save(Counter
                .builder()
                .count(0)
                .staff(registered)
                .build());

        String appUrl = "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

        eventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping
    @RequestMapping(value = "/cook")
    public ResponseEntity<?> registerCookAccount(
            @RequestBody @Valid Staff account, HttpServletRequest request){

        LOGGER.debug("Registering cook account with information {}", account);

        Staff registered = staffService.saveStaff(account, "ROLE_COOK");
        counterRepository.save(Counter
                .builder()
                .count(0)
                .staff(registered)
                .build());

        String appUrl = "http://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

        eventPublisher.publishEvent(
                new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
