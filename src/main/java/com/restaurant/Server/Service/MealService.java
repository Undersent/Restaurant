package com.restaurant.Server.Service;

import com.restaurant.Server.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;


public interface MealService {
    Optional<Meal> findByMealName(String name);
    Optional<Meal> findByMealId(int id);

    Page<Meal> findAll(Pageable pageable);
    Collection<Meal> findMealsByPriceBetween(double lowerLimit, double higherLimit);
    Collection<Meal> findMealsByPriceBelow(double limit);

    void UpdateMealById(Meal newMeal);
    void saveMeal(Meal meal);

}
