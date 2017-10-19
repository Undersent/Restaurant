package com.restaurant.Server.Service;

import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;

import java.util.List;

public interface StaffService {
    Staff findStaffByFirstName(String firstName);
    Staff findStaffByLastName(String lastName);
    Staff findStaffById(int id);
    Staff findStaffByPesel(String pesel);
    void saveStaff(Staff staff, String role);
}
