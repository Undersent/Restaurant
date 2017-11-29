package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add/meal")
public class AddMealController {

    MealService mealService;

    @PostMapping
    public ResponseEntity<?> addMeal(@RequestBody Meal meal){
        this.validateMealName(meal.getMealName());

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
