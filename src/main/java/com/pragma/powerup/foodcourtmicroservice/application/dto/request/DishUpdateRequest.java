package com.pragma.powerup.foodcourtmicroservice.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DishUpdateRequest {
    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private BigDecimal price;
    @NotBlank(message = "Description is required")
    private String description;
    @NotNull(message = "Owner ID is required for validation")
    private Long ownerId;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
