package com.restaurant.Server.controllers.admin.delete;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.exceptions.MealNotFoundException;
import com.restaurant.Server.model.Meal;
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
@RequestMapping("admin/delete/staff")
public class DeleteStaffController {

    StaffService staffService;

    @DeleteMapping
    public ResponseEntity<?> deleteStaff(@RequestBody Staff staff){

        Staff staffFound = staffService.findStaffById(staff.getStaffId())
                .orElseThrow(() ->new MealNotFoundException(staff.getStaffId()));
        staffService.removeStaff(staff.getStaffId());

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
