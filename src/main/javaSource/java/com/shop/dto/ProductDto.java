package com.shop.dto;


import com.shop.entities.Product;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by ostap on 3/4/17.
 */
public class ProductDto {

    private int id;
    @NotEmpty
    @NotNull
    private String name;

    @NotEmpty
    @NotNull
    private String category;

    @NotEmpty
    @NotNull
    private String type;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    @NotEmpty
    @NotNull
    private String manufacturer;

    private String description;

    private byte[] image;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.name = product.getName();
        this.category = product.getCategory();
        this.type = product.getType();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.id = product.getId();
        this.manufacturer = product.getManufacturer();
        this.description = product.getDescription();
        this.image = product.getImage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
