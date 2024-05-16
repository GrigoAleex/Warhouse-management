package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;

public class OrderDAO extends ModelDAO<Order> {
    @Override
    protected String tableName () {
        return "orders";
    }

    @Override
    protected Class<Order> model () {
        return Order.class;
    }
}
