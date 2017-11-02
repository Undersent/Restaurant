package com.restaurant.Server.Service;

import com.restaurant.Server.model.Counter;

import java.util.Collection;

public interface CounterService {
    Collection<Counter> findAll();
    void save(Counter counter);
}
