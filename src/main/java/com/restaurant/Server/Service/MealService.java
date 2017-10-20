package com.restaurant.Server.Service;

import com.restaurant.Server.model.Meal;

public interface MealService {
    Meal findByMealName(String name);

    void saveMeal(Meal meal);
}
