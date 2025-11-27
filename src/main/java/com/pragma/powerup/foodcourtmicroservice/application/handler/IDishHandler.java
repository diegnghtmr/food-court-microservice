package com.pragma.powerup.foodcourtmicroservice.application.handler;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishUpdateRequest;

public interface IDishHandler {
    DishResponse createDish(DishRequest dishRequest);
    DishResponse updateDish(Long id, DishUpdateRequest dishUpdateRequest);
}
