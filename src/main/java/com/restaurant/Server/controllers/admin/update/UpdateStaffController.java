package com.restaurant.Server.controllers.admin.update;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/update/staff")
public class UpdateStaffController {

    StaffService staffService;

    @PutMapping
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff){
        validateStaff(staff.getPesel());
        this.staffService.UpdateStaffById(staff);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateStaff(String pesel) {
    }
}
