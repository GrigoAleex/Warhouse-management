package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.OrderDAO;
import com.example.pt2024_30223_grigorescu_alexandru_assignment_3.data.daos.ProductDAO;

public class OrderProduct extends Model{
    @Column(name="id", type="Int")
    private int id = -1;

    @Column(name="product_id", type="Int")
    private Integer productId;

    @Column(name="order_id", type="Int")
    private Integer orderId;

    public OrderProduct() {}

    public Product product() {
        return new ProductDAO().find(productId);
    }

    public Order order() {
        return new OrderDAO().find(orderId);
    }

    public int getId() {
        return id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
