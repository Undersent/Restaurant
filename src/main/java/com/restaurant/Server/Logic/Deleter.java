package com.restaurant.Server.Logic;

import com.restaurant.Server.model.Orders;

@FunctionalInterface
public interface Deleter {
    void removeOrder(Orders order);
}
