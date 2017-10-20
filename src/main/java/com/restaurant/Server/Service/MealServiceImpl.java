package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.MealRepository;
import com.restaurant.Server.model.Meal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
@Transactional
public class MealServiceImpl implements MealService {

    private MealRepository mealRepository;

    @Override
    public Meal findByMealName(String name) {
        return mealRepository.findByMealName(name);
    }

    @Override
    @Transactional
    public void saveMeal(Meal meal) {
        mealRepository.save(meal);
    }
}
