package com.pragma.powerup.foodcourtmicroservice.domain.usecase;

import com.pragma.powerup.foodcourtmicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.CategoryModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IDishPersistencePort;
import java.math.BigDecimal;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public DishModel createDish(DishModel dishModel, Long requestOwnerId) {
        // 1. Validate Restaurant existence
        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(dishModel.getRestaurantId());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }

        // 2. Validate Ownership
        if (!restaurant.getOwnerId().equals(requestOwnerId)) {
            throw new IllegalStateException("User is not the owner of this restaurant");
        }

        // 3. Validate Category existence
        CategoryModel category = dishPersistencePort.getCategoryById(dishModel.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        
        // 4. Validate Price
        if (dishModel.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
             throw new IllegalArgumentException("Price must be greater than 0");
        }

        // 5. Set Initial State
        dishModel.setActive(true);

        // 6. Save
        return dishPersistencePort.saveDish(dishModel);
    }
}