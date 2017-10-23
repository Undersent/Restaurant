package com.restaurant.Server.controllers.admin.update;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/update/meal")
public class UpdateMealController {

    MealService mealService;
    //http://localhost:8080/admin/update/meal , i wyslac jsona {"mealName":"fryteczki","available":0,"price":6.66}
    @PutMapping
    public ResponseEntity<?> updateMeal(@RequestBody Meal meal){
        validateMeal(meal.getMealId());
        this.mealService.UpdateMealById(meal);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    private void validateMeal(int id) {
        this.mealService.findByMealId(id)
                .orElseThrow(
                         EntityNotFoundException::new
                );
    }
}
