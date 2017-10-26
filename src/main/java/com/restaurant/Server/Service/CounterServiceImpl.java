package com.restaurant.Server.Service;

import com.restaurant.Server.Repository.CounterRepository;
import com.restaurant.Server.model.Counter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@AllArgsConstructor(onConstructor = @_(@Autowired))
@Service
@NoArgsConstructor
public class CounterServiceImpl implements CounterService {

    CounterRepository counterRepository;

    @Override
    public Collection<Counter> findAll() {
        return counterRepository.findAll();
    }
}
