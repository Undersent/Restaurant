package com.restaurant.Server.repository;

import com.restaurant.Server.Repository.MealRepository;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@DataJpaTest
public class MealRepositoryTest {

    @Autowired
    MealRepository mealRepository;
    @Autowired
    EntityManager entityManager;

    private Meal meal1 = Meal
            .builder()
            .isAvailable(true)
            .mealName("fryteczki")
            .price(4)
            .build();
    private Meal meal2 = Meal
            .builder()
            .isAvailable(true)
            .mealName("kawunia")
            .price(5.50)
            .build();

    @Test
    public void getMealsWithPriceBetweenTest(){
        entityManager.persist(meal1);
        entityManager.persist(meal2);
        entityManager.flush();

        assertEquals(1,
                mealRepository.findMealsByPriceBetween(3, 5).stream()
                        .filter(meal -> Objects.equals(meal.getMealName(), "fryteczki"))
                        .count());
        assertEquals(1,
                mealRepository.findMealsByPriceBetween(4, 5).stream()
                        .filter(meal -> Objects.equals(meal.getMealName(), "fryteczki"))
                        .count());
    }

    @Test
    public void getMealsWithPriceBelowTest(){
        entityManager.persist(meal1);
        entityManager.persist(meal2);
        entityManager.flush();

        assertEquals(2,
                mealRepository.findMealsByPriceBefore(6).stream()
                        .filter(meal -> meal.getPrice() < 6)
                        .count());

        assertEquals(1,
                mealRepository.findMealsByPriceBefore(5).stream()
                        .filter(meal -> meal.getPrice() < 5)
                        .count());
    }
}
