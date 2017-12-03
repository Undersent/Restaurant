package com.restaurant.Server.controllers.admin.update;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/update/staff")
public class UpdateStaffController {

    StaffService staffService;

    @PostMapping
    public ResponseEntity<?> updateStaff(@RequestBody Staff staff){
        Optional<Staff> optionalStaff = staffService.findStaffByPesel(staff.getPesel());
        if (!optionalStaff.isPresent()) {
            throw new RuntimeException();
        } else {
            Staff actualStaff = optionalStaff.get();
            actualStaff.setRoles(staff.getRoles());
            actualStaff.setFirstName(staff.getFirstName());
            actualStaff.setLastName(staff.getLastName());
            this.staffService.UpdateStaffById(actualStaff);

            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }
    }
}
