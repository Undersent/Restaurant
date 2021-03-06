package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
@Deprecated
@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add/staff/cook")
public class AddStaffCookController {

    private StaffService staffService;
    private CounterRepository counterRepository;


    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
        validateStaff(staff.getPesel());

        staffService.saveStaff(staff, "ROLE_COOK");
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
                .filter(staff -> staff.getPesel().equals(pesel))
                .ifPresent(s -> {
                    throw new RuntimeException("staff with that pesel exists");
                });
    }
}
