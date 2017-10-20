package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("admin/add")
public class mealController {

    MealService mealService;

    @GetMapping(value = {"/meal"})
    public ModelAndView addMeal(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/add/meal");
        return modelAndView;
    }

    @PostMapping(value = {"/meal"})
    public ModelAndView getMeal(ModelAndView modelAndView, @RequestParam Map requestParams){
        modelAndView.setViewName("admin/add/meal");
        Meal meal = Meal.builder()
                .mealName((String) requestParams.get("meal_name"))
                .isAvailable(isTrue(requestParams))
                .price(getValueOf(requestParams))
                .build();

        mealService.saveMeal(meal);
        modelAndView.addObject("successMessage", "Your meal has been added!");

        return modelAndView;
    }

    //http://localhost:8080/admin/add/mealList?page=0&size=3
    @GetMapping(value={"/mealList"})
    Page<Meal> mealList(Pageable pageable){
        Page<Meal> meals = mealService.findAll(pageable);
        return meals;
    }

    private double getValueOf(Map requestParams) {
        return Double.parseDouble(String.valueOf( requestParams.get("price")));
    }

    private boolean isTrue(Map requestParams) {
        return Double.parseDouble(String.valueOf(requestParams.get("is_available"))) == 1;
    }
}
