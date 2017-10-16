package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByFirstName(String firstName);
    Staff findByLastName(String lastName);
}
