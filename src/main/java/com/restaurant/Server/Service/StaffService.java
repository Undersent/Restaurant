package com.restaurant.Server.Service;

import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    Optional<Staff> findStaffByFirstName(String firstName);
    Optional<Staff> findStaffByLastName(String lastName);
    Optional<Staff> findStaffById(int id);
    Optional<Staff> findStaffByPesel(String pesel);

    void saveStaff(Staff staff, String role);
    void UpdateStaffById(int id, String name, double price, boolean isAvailable);

}
