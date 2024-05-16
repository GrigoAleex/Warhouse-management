package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.Product;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models.User;

public class ProductDAO extends ModelDAO<Product> {
    @Override
    protected String tableName () {
        return "products";
    }

    @Override
    protected Class<Product> model () {
        return Product.class;
    }
}
