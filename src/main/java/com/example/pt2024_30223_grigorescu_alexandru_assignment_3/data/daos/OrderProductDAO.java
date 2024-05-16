package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Order;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.OrderProduct;

public class OrderProductDAO extends ModelDAO<OrderProduct> {
    @Override
    protected String tableName () {
        return "product_order";
    }

    @Override
    protected Class<OrderProduct> model () {
        return OrderProduct.class;
    }
}
