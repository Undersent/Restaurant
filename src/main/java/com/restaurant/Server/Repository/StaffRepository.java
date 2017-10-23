package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByFirstName(String firstName);
    Optional<Staff> findByLastName(String lastName);
    Optional<Staff> findByStaffId(int id);
    Optional<Staff> findByPesel(String pesel);
    Page<Staff> findAll(Pageable pageable);
}
