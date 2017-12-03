package com.restaurant.Server.controllers.staff;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Role;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("get/staff")
public class GetStaffController {

    StaffService staffService;

    @GetMapping(value={"/lastName"})
    public Staff getStaffByName(@RequestParam("lastName") String lastName){
        return this.staffService.findStaffByLastName(lastName).get();
    }

    @GetMapping(value ={"/id"})
    public Staff getStaffById(@RequestParam("id") int id){
        return this.staffService.findStaffById(id).get();
    }

    @GetMapping(value={"/all"})
    public List<Staff> staffList(){
        return this.staffService
                .findAll(PageRequest.of(0,20))
                .filter(this::hasStaffRole)
                .stream()
                .collect(Collectors.toList());
    }

    private boolean hasStaffRole(Staff staff) {
        Set<Role> roles = staff.getRoles();
        for (Role role: roles){
            if (role.getRole().equals("ROLE_STAFF")){
                return true;
            }
        }
        return false;
    }
}
