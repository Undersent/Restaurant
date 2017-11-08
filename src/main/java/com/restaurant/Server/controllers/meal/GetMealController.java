package com.restaurant.Server.controllers.meal;

import com.restaurant.Server.Service.MealService;
import com.restaurant.Server.Service.OrdersService;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor(onConstructor = @_(@Autowired))
@RequestMapping("get/meal")
public class GetMealController {

    MealService mealService;
    OrdersService ordersService;

    @GetMapping
    public Meal getMealByName(@RequestParam("name") String name){
        validateMealName(name);
        return this.mealService.findByMealName(name).get();
    }

    @GetMapping(value ="/{id}")
    public Meal getMealById(@PathVariable("id") int id){
        return this.mealService.findByMealId(id).get();
    }

    //http://localhost:8080/get/meal/all?page=0&size=4&sort=price,desc   //http://localhost:8080/get/meal/all?page=0&size=3
    @GetMapping(value={"/all"})
    Page<Meal> mealList(Pageable pageable){
       return this.mealService.findAll(pageable);
    }

    @GetMapping(value = {"/price"}) //http://localhost:8080/get/meal/price?lp=8&hp=12
    public Collection<Meal> getMealByPriceBetween(@RequestParam("lp") Double lowerPrice,
                                                  @RequestParam("hp") Double higherPirce ){
        return this.mealService.findMealsByPriceBetween(lowerPrice, higherPirce);
    }

    private void validateMealName(String name) {
        this.mealService.findByMealName(name).orElseThrow(
                EntityNotFoundException::new
        );
    }

}
