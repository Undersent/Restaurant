package com.restaurant.Server.Logic;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.Repository.MealRepository;
import com.restaurant.Server.Repository.OrdersRepository;
import com.restaurant.Server.Repository.StaffRepository;
import com.restaurant.Server.Service.CounterService;
import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.Service.StaffService;
import com.restaurant.Server.model.*;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import java.util.Comparator;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
public class AssignerOfOrdersTest {

    @Autowired
    private Assigner assigner;
    @Autowired
    private CounterRepository counterRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrdersService ordersService;

    private String mealName = "kurczak";
    private Meal meal = Meal.builder()
            .mealName(mealName)
            .build();

    private Customer customer = Customer.builder().build();
    private Orders order = Orders.builder()
            .meal(meal)
            .customer(customer)
            .build();

    private Staff staff = Staff.builder().isAvailable(true).lastName("a").firstName("b").enabled(true).pesel("123").build();
    private Staff staff2 = Staff.builder().isAvailable(true).lastName("c").firstName("d").enabled(true).pesel("124").build();

    private Counter counter = Counter.builder()
            .count(0)
            .staff(staff)
            .build();
    private Counter counter2 = Counter.builder()
            .count(0)
            .staff(staff2)
            .build();
    @Before
    public void setUp() throws Exception {
        this.counterRepository.deleteAllInBatch();
        this.mealRepository.deleteAllInBatch();
        this.ordersRepository.deleteAllInBatch();
        counterRepository.save(counter);
        counterRepository.save(counter2);
    }

    @Test
    public void assignOrder() throws Exception {
        this.ordersService.save(order);
        assigner.assignOrder(customer, order);
        this.ordersService.save(order);
        assigner.assignOrder(customer, order);
        this.ordersService.save(order);
        assigner.assignOrder(customer, order);
        this.ordersService.save(order);
        assigner.assignOrder(customer, order);
        this.ordersService.save(order);
        assigner.assignOrder(customer, order);

        assertEquals(true, counterRepository.findAll()
                .stream()
                .min(Comparator.comparingInt(Counter::getCount))
                .filter(c -> c.getCount() == 2)
                .isPresent());

        assertEquals(true, counterRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(Counter::getCount))
                .filter(c -> c.getCount() == 3)
                .isPresent());

    }
}