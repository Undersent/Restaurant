package com.restaurant.Server.controllers.admin.delete;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.exceptions.MealNotFoundException;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/delete/meal")
public class DeleteMealController{

    MealService mealService;

@DeleteMapping
public ResponseEntity<?> addMeal(@RequestBody Meal meal){

        Meal mealFound = mealService.findByMealName(meal.getMealName())
                .orElseThrow(() ->new MealNotFoundException(meal.getMealId()));
        mealService.removeMeal(meal.getMealId());
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }

}
