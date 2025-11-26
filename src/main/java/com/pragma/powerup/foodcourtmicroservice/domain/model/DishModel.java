package com.pragma.powerup.foodcourtmicroservice.domain.model;

import java.math.BigDecimal;

public class DishModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long restaurantId;
    private Long categoryId;
    private boolean active;

    public DishModel() {
    }

    public DishModel(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Long restaurantId,
        Long categoryId,
        boolean active
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurantId = restaurantId;
        this.categoryId = categoryId;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
