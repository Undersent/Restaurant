package com.restaurant.Server.repository;

import com.restaurant.Server.Repository.StaffRepository;
import com.restaurant.Server.model.Role;
import com.restaurant.Server.model.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DataJpaTest
public class StaffRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StaffRepository staffRepository;

    private String firstName = "Bozena";
    private String lastName = "Pasztecik";

    private Staff createStaffWaiter(){
        return Staff
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .roles(new HashSet<>(Collections
                        .singletonList(entityManager
                                .find(Role.class, 1))))
                .build();
    }

    @Test
    public void findByFirstName(){
        Staff staff = createStaffWaiter();
        entityManager.merge(staff);
        entityManager.flush();

        Staff staffFound = staffRepository.findByFirstName(firstName);

        assertNotNull(staffFound);
        assertThat(staffFound.getFirstName())
                .isEqualToIgnoringCase(staff.getFirstName());
    }
}
