package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add/staff/{pesel}")
public class AddStaffController {

    StaffService staffService;

    @PostMapping
    public ResponseEntity<?> addStaff(@PathVariable("pesel") String pesel,
                                   @RequestBody(required = false) Staff staff){
        validateStaff(pesel);
        staff.setPesel(pesel);
        staffService.saveStaff(staff, "ROLE_STAFF");
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateStaff(String pesel) { //POPRAWIC!
        this.staffService
                .findStaffByPesel(pesel)
                .filter(pesel::equals)
                .ifPresent(s -> {
                    new Exception("staff with that pesel exists");
                });
    }

    ;
}
