package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Integer> {
    Optional<Meal> findByMealName(String name);
    Optional<Meal> findByPrice(double price);
    Optional<Meal> findByMealId(int id);
    Page<Meal> findAll(Pageable pageable);
    Collection<Meal> findMealsByPriceBetween(double lowerLimit, double higherLimit);
    Collection<Meal> findMealsByPriceBefore(double limit);

}
