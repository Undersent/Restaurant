package com.restaurant.Server.repository;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.model.Counter;
import com.restaurant.Server.model.Orders;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DataJpaTest
public class CounterRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    CounterRepository counterRepository;

    Orders order = Orders
            .builder()
            .id(0)
            .dateOfOrder("10.10.10")
            .build();
    Orders order2 = Orders
            .builder()
            .id(1)
            .dateOfOrder("06.06.06")
            .build();
    Orders order3 = Orders
            .builder()
            .id(2)
            .dateOfOrder("06.06.06")
            .build();

//    Staff staff1 = Staff
//            .builder()
//            .firstName("a")
//            .lastName("a")
//            .orders(Stream.of(order, order2)
//                    .collect(Collectors.toList()))
//            .isAvailable(true)
//            .build();
//
//    Staff staff2 = Staff
//            .builder()
//            .firstName("b")
//            .lastName("b")
//            .orders(Stream.of(order3)
//                    .collect(Collectors.toList()))
//            .isAvailable(true)
//            .build();
//
//
//    @Test
//    public void GetCustomerWithTheLeastNoOfOrders() {
//        Counter counter = Counter
//                .builder()
//                .count(staff1.getOrders().size())
//                .staff(staff1)
//                .build();
//
//        Counter counter2 = Counter
//                .builder()
//                .count(staff2.getOrders().size())
//                .staff(staff2)
//                .build();
//
//        entityManager.merge(counter);
//        entityManager.merge(counter2);
//        entityManager.flush();
//
//        counterRepository.findAll()
//                .stream()
//                .filter(c -> c.getCount() == staff2.getOrders().size())
//                .forEach(c -> assertEquals(c.getStaff().getFirstName(), "b"));
//
//        assertEquals(counterRepository.findAll()
//                .stream()
//                .min(Comparator.comparingInt(Counter::getCount)).get().getCount(), staff2.getOrders().size());

    //}
}

