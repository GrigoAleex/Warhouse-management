package com.example.pt2024_30223_grigorescu_alexandru_assignment_3.business.models;

public class Product extends Model{
    @Column(name="id", type="Int")
    private int id = -1;

    @Column(name="name", type="String")
    private String name;

    @Column(name="stock", type="Int")
    private Integer stock;

    @Column(name="barcode", type="String")
    private String barCode;

    @Column(name="image", type="String")
    private String image = "/products/default.png";

    public Product() {}

    public Product(String name, Integer stock, String barCode) {
        this.name = name;
        this.stock = stock;
        this.barCode = barCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
