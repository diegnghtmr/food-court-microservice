package com.pragma.powerup.foodcourtmicroservice.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishActiveRequest {
    @NotNull(message = "Active status is required")
    private Boolean active;
}
