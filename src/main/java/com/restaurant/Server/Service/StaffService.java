package com.restaurant.Server.Service;

import com.restaurant.Server.model.Staff;

public interface StaffService {
    Staff findStaffByFirstName(String firstName);
    Staff findStaffByLastName(String lastName);
    void saveStaff(Staff staff, String role);
}
