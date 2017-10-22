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
@RequestMapping("admin/update/meal/{id}")
public class UpdateMealController {

    MealService mealService;
    //http://localhost:8080/admin/update/meal/1 , a potem wyslac jsona {"mealName":"fryteczki","isAvailable":0,"price":6.66}
    @PutMapping
    public ResponseEntity<?> updateMeal(@PathVariable("id") int id,
                                     @RequestBody(required = false) Meal meal){
        validateMeal(id);
        meal.setMealId(id);
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
