package com.restaurant.Server.controllers.admin.add;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
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
                .isAvailable((boolean) requestParams.get("is_available"))
                .build();

        mealService.saveMeal(meal);
        modelAndView.addObject("successMessage", "Your password has been set!");

        return modelAndView;
    }
}
