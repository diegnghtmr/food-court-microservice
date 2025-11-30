package com.pragma.powerup.foodcourtmicroservice.application.handler;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishUpdateRequest;

import java.util.List;

public interface IDishHandler {
    DishResponse createDish(DishRequest dishRequest);
    DishResponse updateDish(Long id, DishUpdateRequest dishUpdateRequest);
    DishResponse updateDishActiveState(Long id, boolean isActive);
    List<DishResponse> getDishesByRestaurant(Long restaurantId, Integer page, Integer size);
    DishResponse getDish(Long id);
}
