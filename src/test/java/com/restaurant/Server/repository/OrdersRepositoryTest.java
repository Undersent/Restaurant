package com.restaurant.Server.repository;

import com.restaurant.Server.Repository.OrdersRepository;
import com.restaurant.Server.model.*;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DataJpaTest
//@ActiveProfiles("test")
public class OrdersRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrdersRepository ordersRepository;

    private final Appender appender = mock(Appender.class);
    private final Logger logger = Logger.getRootLogger();

    private Customer customer = createCustomer(1);

    private Orders order = Orders
            .builder()
            .id(1)
            .customer(customer)
          //  .staffId(customer.getCustomerId())
            .meal(Meal.builder().mealId(6).mealName("b").build())
            .staffId(2)
            .build();

    private Orders order2 = Orders
            .builder()
            .id(2)
            .meal(Meal.builder().mealId(5).mealName("a").build())
            .customer(customer)
            //.staffId(customer.getCustomerId())
            //.staffId(2)
            .build();


    @Before
    public void setUp(){
        entityManager.clear();
        logger.addAppender(appender);
    }

    @After
    public void tearDown(){
        Logger.getLogger(OrdersRepositoryTest.class).info("COUNT: "+ordersRepository.count());
    }

    private Customer createCustomer(int id){
        return Customer
                .builder()
                .customerId(id)
                .build();
    }

    @Test
    public void findAllOrdersTest(){
        entityManager.merge(customer);
        entityManager.merge(order);
        entityManager.merge(order2);
        entityManager.flush();

        List<Orders> orders = ordersRepository.findAll();

        assertEquals(orders.size(),2);
        assertEquals("b", orders.get(0).getMeal().getMealName());
        assertTrue(orders.stream().anyMatch(meal -> meal.getMeal().getMealName().equals("b")));
    }

    @Test
    public void findOrderByCustomerTest(){
        //order.setCustomer(customer);
//        customer
//                .setOrder(Stream.of(order, order2)
//                    .collect(Collectors.toCollection(ArrayList::new)));
        entityManager.merge(order);
        entityManager.merge(order2);
        entityManager.flush();

        Collection<Orders> orders = ordersRepository.findAllByCustomer(customer);

        assertEquals(2, orders.size());
        assertNotNull(orders);
        assertEquals(2, orders.stream()
                .filter(o -> o.getMeal().getMealName().equals("b")
                        || o.getMeal().getMealName().equals("a"))
                .count());
    }

    @Test(expected = RuntimeException.class)
    public void findAllOrdersByCustomerTest(){
        Customer newCustomer = Customer.builder().customerId(666).build();
        entityManager.persistAndFlush(newCustomer);
        entityManager.merge(order);
        entityManager.merge(order2);
        entityManager.flush();

        Collection<Orders> orders = ordersRepository.findAllByCustomer(newCustomer);

        Orders ord = orders.stream().findAny().orElseThrow(() -> new RuntimeException("ups"));
    }

}