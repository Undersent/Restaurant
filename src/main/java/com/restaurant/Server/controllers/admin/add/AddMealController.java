package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add/meal")
public class AddMealController {

    MealService mealService;

    @PostMapping(value = "/new")
    public ResponseEntity<?> addMeal(@RequestBody Meal meal){
        this.validateMealName(meal.getMealName());
        mealService.saveMeal(meal);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteMeal(@RequestParam(value = "id") int id){
        mealService.removeMeal(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> updateMeal(@RequestBody Meal meal){
        mealService.saveMeal(meal);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateMealName(String mealName) {
        this.mealService
                .findByMealName(mealName)
                .filter(m -> m.getMealName().equalsIgnoreCase(mealName))
                .ifPresent(m -> {
                   throw new RuntimeException("meal with that name exists");
                });
    }
}
