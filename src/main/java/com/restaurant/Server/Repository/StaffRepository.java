package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Staff;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByFirstName(String firstName);
    Staff findByLastName(String lastName);
    Staff findByStaffId(int id);
    Staff findByPesel(String pesel);

}
