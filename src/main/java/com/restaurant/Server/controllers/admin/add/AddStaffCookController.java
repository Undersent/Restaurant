package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin/add/staff/cook")
public class AddStaffCookController {

    StaffService staffService;

    @PostMapping
    public ResponseEntity<?> addStaff(@RequestBody Staff staff){
        validateStaff(staff.getPesel());
        staffService.saveStaff(staff, "ROLE_COOKER");
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateStaff(String pesel) { //POPRAWIC wszedzie TODO!
        this.staffService
                .findStaffByPesel(pesel)
                .filter(pesel::equals)
                .ifPresent(s -> {
                    new Exception("staff with that pesel exists");
                });
    }
}
