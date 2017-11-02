package com.restaurant.Server.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class EntitiesTest {
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createStaffWaiter(){
        Staff staff = Staff
                .builder()
                .firstName("Bozena")
                .lastName("Dobrabula")
                .roles(new HashSet<>(Collections
                        .singletonList(entityManager
                                .find(Role.class, 1))))
                .build();
        entityManager.merge(staff);
        entityManager.flush();
    }
}
