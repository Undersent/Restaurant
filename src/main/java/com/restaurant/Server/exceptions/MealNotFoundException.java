package com.restaurant.Server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MealNotFoundException extends RuntimeException{

    public MealNotFoundException(int mealId) {
        super("could not find meal with id: " + mealId);
    }
}
