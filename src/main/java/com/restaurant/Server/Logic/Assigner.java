package com.restaurant.Server.Logic;

import com.restaurant.Server.model.Customer;
import com.restaurant.Server.model.Orders;

public interface Assigner {
    void assignOrder(Customer customer,Orders order);
}
