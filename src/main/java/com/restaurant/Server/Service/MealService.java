package com.restaurant.Server.Service;

import com.restaurant.Server.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MealService {
    Meal findByMealName(String name);
    Page<Meal> findAll(Pageable pageable);
    void saveMeal(Meal meal);
}
