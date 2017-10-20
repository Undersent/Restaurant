package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Meal findByMealName(String name);
    Meal findByPrice(double price);
    Page<Meal> findAll(Pageable pageable);
    List<Meal> findMealsByPriceBetween(double lowerLimit, double higherLimit);
    List<Meal> findMealsByPriceBefore(double limit);
}
