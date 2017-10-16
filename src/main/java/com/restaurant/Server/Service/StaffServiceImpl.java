package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.RoleRepository;
import com.restaurant.Server.Repository.StaffRepository;
import com.restaurant.Server.model.Staff;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
@NoArgsConstructor
public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepository;
    private RoleRepository roleRepository;

    @Override
    public Staff findStaffByFirstName(String firstName) {
        return staffRepository.findByFirstName(firstName);
    }

    @Override
    public Staff findStaffByLastName(String lastName) {
        return staffRepository.findByLastName(lastName);
    }

    @Override
    public void saveStaff(Staff staff, String role) {
//        staff.setUserRole(new HashSet<>(Collections
//                .singletonList(roleRepository
//                        .findByRole(role))));
        staffRepository.save(staff);
    }
}
