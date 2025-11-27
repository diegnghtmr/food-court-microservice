package com.pragma.powerup.foodcourtmicroservice.domain.api;

import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    DishModel createDish(DishModel dishModel, Long requestOwnerId);
    DishModel updateDish(Long id, DishModel dishModel, Long requestOwnerId);
    DishModel updateDishActiveState(Long id, boolean isActive, Long requestOwnerId);
    List<DishModel> getDishesByRestaurant(Long restaurantId, Integer page, Integer size);
}