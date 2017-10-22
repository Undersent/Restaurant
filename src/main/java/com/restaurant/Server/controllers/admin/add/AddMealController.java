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
@RequestMapping("admin/add/meal/{mealName}")
public class AddMealController {

    MealService mealService;

    @PostMapping//http://localhost:8080/admin/add/meal/pizza2?price=10&isAvailable=1
    public ResponseEntity<?> postMeal(@PathVariable("mealName") String mealName,
                               @RequestParam Map requestParams){
        this.validateMealName(mealName);

        mealService.saveMeal(
                Meal.builder()
                        .mealName(mealName)
                        .available(isTrue(requestParams))
                        .price(getValueOf(requestParams))
                        .build()
        );
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateMealName(String mealName) {
        this.mealService
                .findByMealName(mealName)
                .filter(mealName::equals)
                .ifPresent(m -> {
                   new Exception("meal with that name exists");
                });
    }


    private double getValueOf(Map requestParams) {
        return Double.parseDouble(String.valueOf( requestParams.get("price")));
    }

    private boolean isTrue(Map requestParams) {
        return Double.parseDouble(String.valueOf(requestParams.get("isAvailable"))) == 1;
    }
}
