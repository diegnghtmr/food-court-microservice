package com.pragma.powerup.foodcourtmicroservice.application.handler;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;

public interface IDishHandler {
    DishResponse createDish(DishRequest dishRequest);
}
