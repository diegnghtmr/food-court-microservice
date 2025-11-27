package com.pragma.powerup.foodcourtmicroservice.domain.api;

import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;

public interface IDishServicePort {
    DishModel createDish(DishModel dishModel, Long requestOwnerId);
    DishModel updateDish(Long id, DishModel dishModel, Long requestOwnerId);
}