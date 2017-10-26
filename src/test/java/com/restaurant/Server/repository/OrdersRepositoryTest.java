package com.restaurant.Server.repository;

import com.restaurant.Server.Repository.OrdersRepository;
import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;
import com.restaurant.Server.model.Role;
import com.restaurant.Server.model.Staff;
import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DataJpaTest
public class OrdersRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrdersRepository ordersRepository;

    private final Appender appender = mock(Appender.class);
    private final Logger logger = Logger.getRootLogger();

    private String firstName = "Bozena";
    private String lastName = "Pasztecik";

    Customer customer = createCustomer(1);
    Orders order = Orders
            .builder()
            .id(1)
            .dateOfOrder("10.10.10")
            .staffId(customer.getCustomerId())
            .build();
    Orders order2 = Orders
            .builder()
            .id(2)
            .dateOfOrder("06.06.06")
            .staffId(customer.getCustomerId())
            .build();


    @Before
    public void setUp(){
        logger.addAppender(appender);
       // Logger.getLogger(OrdersRepositoryTest.class).info("START12: "+);
    }

    @After
    public void tearDown(){
        Logger.getLogger(OrdersRepositoryTest.class).info("COUNT: "+ordersRepository.count());
        entityManager.clear();
    }

    private Staff createStaffWaiter(String fn, String ln){
        return Staff
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .roles(new HashSet<>(Collections
                        .singletonList(entityManager
                                .find(Role.class, 1))))
                .build();
    }

    private Customer createCustomer(int id){
        return Customer
                .builder()
                .customerId(id)
                .build();
    }

    private Orders createOrders(int id){
        return Orders
                .builder()
                .id(id)
                .build();
    }

    @Test
    public void findAllOrdersTest(){
        Orders order = createOrders(1);
        Orders order2 = createOrders(2);

        entityManager.merge(order);
        entityManager.merge(order2);
        entityManager.flush();

        List<Orders> orders = ordersRepository.findAll();
        assertEquals(orders.get(0).getId(), 1);
        assertEquals(orders.get(1).getId(), 2);
    }

    @Test
    public void findOrderByCustomerTest(){

        Orders order = createOrders(1);
        order.setCustomer(customer);
        customer
                .setOrder(Stream.of(order)
                    .collect(Collectors.toCollection(ArrayList::new)));

        entityManager.merge(customer);
        entityManager.flush();

        Optional<Orders> orders = ordersRepository.findByCustomer(customer);

        assertNotNull(orders);
        assertEquals(orders.get().getCustomer().getCustomerId(), 1);
    }

    @Test
    public void findAllOrdersByCustomerTest(){
        Customer customer = createCustomer(1);

//        customer
//                .setOrder(Stream.of(order, order2)
//                        .collect(Collectors.toCollection(ArrayList::new)));
        //order.setCustomer(customer);
        //order2.setCustomer(customer);//relacja dwukierunkowa

        entityManager.merge(order);
        entityManager.merge(order2);
        entityManager.flush();

        Collection<Orders> orders = ordersRepository.findAllByCustomer(customer);

        assertEquals(orders.size(), 2);
        orders.stream()
                .filter(o -> o.getId() == 2)
                .forEach(h -> assertEquals(h.getId(), 2));
    }

//    @Test
//    public void findOrderByCustomerId(){
//        entityManager.merge(order);
//        entityManager.merge(order2);
//        entityManager.flush();
//        Collection<Orders> orders = ordersRepository.findByCustomerId(1);
//        assertEquals(orders.size(), 2);
//
//    }
}