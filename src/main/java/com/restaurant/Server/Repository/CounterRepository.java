package com.restaurant.Server.Repository;

import com.restaurant.Server.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CounterRepository extends JpaRepository<Counter, Integer> {

}
