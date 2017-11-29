package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.RoleRepository;
import com.restaurant.Server.Repository.StaffRepository;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;


@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
@NoArgsConstructor
public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepository;
    private RoleRepository roleRepository;

    @Override
    public Optional<Staff> findStaffByFirstName(String firstName) {
        return staffRepository.findByFirstName(firstName);
    }

    @Override
    public Optional<Staff> findStaffByLastName(String lastName) {
        return staffRepository.findByLastName(lastName);
    }

    @Override
    public Optional<Staff> findStaffById(int id) {
        return staffRepository.findByStaffId(id);
    }

    @Override
    public Optional<Staff> findStaffByPesel(String pesel) {
        return staffRepository.findByPesel(pesel);
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepository.findAll(pageable);
    }

    @Override
    public Staff saveStaff(Staff staff, String role) {
        staff.setRoles(new HashSet<>(Collections
                .singletonList(roleRepository
                        .findByRole(role))));
        return staffRepository.save(staff);
    }

    @Override
    public void UpdateStaffById(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public void removeStaff(int staffId) {
        staffRepository.removeByStaffId(staffId);
    }
}
