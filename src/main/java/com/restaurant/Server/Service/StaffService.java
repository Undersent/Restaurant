package com.restaurant.Server.Service;

import com.restaurant.Server.model.Meal;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    Optional<Staff> findStaffByFirstName(String firstName);
    Optional<Staff> findStaffByLastName(String lastName);
    Optional<Staff> findStaffById(int id);
    Optional<Staff> findStaffByPesel(String pesel);
    Page<Staff> findAll(Pageable pageable);
    void saveStaff(Staff staff, String role);
    void UpdateStaffById(Staff staff);

    void removeStaff(int staffId);
}
