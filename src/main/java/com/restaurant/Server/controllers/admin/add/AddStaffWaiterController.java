package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Deprecated
@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add/staff/waiter")
public class AddStaffWaiterController {

    StaffService staffService;
    private CounterRepository counterRepository;

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
        validateStaff(staff.getPesel());

        staffService.saveStaff(staff, "ROLE_STAFF");
        counterRepository.save(Counter
                .builder()
                .count(0)
                .staff(staff)
                .build());

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateStaff(String pesel) {
        this.staffService
                .findStaffByPesel(pesel)
                .ifPresent(s -> {
                    throw new RuntimeException("staff with that pesel exists");
                });
    }
}
