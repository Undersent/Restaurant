package com.restaurant.Server.controllers.staff;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Meal;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("get/staff")
public class GetStaffController {

    StaffService staffService;

    @GetMapping(value={"/lastName"})
    public Optional<Staff> getStaffByName(@RequestParam("lastName") String lastName){
        return this.staffService.findStaffByLastName(lastName);
    }

    @GetMapping(value ={"/id"})
    public Optional<Staff> getStaffById(@RequestParam("id") int id){
        return this.staffService.findStaffById(id);
    }

    @GetMapping(value={"/all"})
    Page<Staff> staffList(Pageable pageable){
        return this.staffService.findAll(pageable);
    }
}
