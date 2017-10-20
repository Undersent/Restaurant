package com.restaurant.Server.controllers.admin;

import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("/admin")
public class AdminController {

    StaffService staffService;

    @GetMapping("/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Staff staff = staffService.findStaffByPesel(auth.getName());
        modelAndView.addObject("firstName", "Welcome "
                + staff.getLastName());
        modelAndView.addObject("adminMessage","Content Available for Admin");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}
