package com.pragma.powerup.foodcourtmicroservice.domain.spi;

import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.CategoryModel;

public interface IDishPersistencePort {
    DishModel saveDish(DishModel dishModel);
    DishModel getDishById(Long id);
    RestaurantModel getRestaurantById(Long id);
    CategoryModel getCategoryById(Long id);
}
