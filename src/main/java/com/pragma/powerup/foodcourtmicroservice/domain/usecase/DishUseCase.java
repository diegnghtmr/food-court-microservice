package com.pragma.powerup.foodcourtmicroservice.domain.usecase;

import com.pragma.powerup.foodcourtmicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.CategoryModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IDishPersistencePort;
import java.math.BigDecimal;

import java.util.List;

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
        validateOwnership(restaurant, requestOwnerId);

        // 3. Validate Category existence
        CategoryModel category = dishPersistencePort.getCategoryById(dishModel.getCategoryId());
        if (category == null) {
            throw new IllegalArgumentException("Category not found");
        }
        
        // 4. Validate Price
        validatePrice(dishModel.getPrice());

        // 5. Set Initial State
        dishModel.setActive(true);

        // 6. Save
        return dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public DishModel updateDish(Long id, DishModel dishModel, Long requestOwnerId) {
        DishModel existingDish = dishPersistencePort.getDishById(id);
        if (existingDish == null) {
            throw new IllegalArgumentException("Dish not found");
        }

        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(existingDish.getRestaurantId());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }

        validateOwnership(restaurant, requestOwnerId);
        validatePrice(dishModel.getPrice());

        existingDish.setPrice(dishModel.getPrice());
        existingDish.setDescription(dishModel.getDescription());

        return dishPersistencePort.saveDish(existingDish);
    }

    @Override
    public DishModel updateDishActiveState(Long id, boolean isActive, Long requestOwnerId) {
        DishModel existingDish = dishPersistencePort.getDishById(id);
        if (existingDish == null) {
            throw new IllegalArgumentException("Dish not found");
        }

        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(existingDish.getRestaurantId());
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }

        validateOwnership(restaurant, requestOwnerId);

        existingDish.setActive(isActive);

        return dishPersistencePort.saveDish(existingDish);
    }

    @Override
    public List<DishModel> getDishesByRestaurant(Long restaurantId, Integer page, Integer size) {
        RestaurantModel restaurant = dishPersistencePort.getRestaurantById(restaurantId);
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        return dishPersistencePort.getDishesByRestaurant(restaurantId, page, size);
    }

    private void validateOwnership(RestaurantModel restaurant, Long requestOwnerId) {
        if (!restaurant.getOwnerId().equals(requestOwnerId)) {
            throw new IllegalStateException("User is not the owner of this restaurant");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
             throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}