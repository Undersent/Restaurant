package com.restaurant.Server.controllers.staff;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Meal;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("get/staff")
public class GetStaffController {

    StaffService staffService;

    @GetMapping(value={"/lastName"})
    public Staff getStaffByName(@RequestParam("lastName") String lastName){
        return this.staffService.findStaffByLastName(lastName).get();
    }

    @GetMapping(value ={"/{id}"})
    public Staff getStaffById(@PathVariable("id") int id){
        return this.staffService.findStaffById(id).get();
    }

    @GetMapping(value={"/all"})
    Page<Staff> staffList(Pageable pageable){
        return this.staffService.findAll(pageable);
    }
}
