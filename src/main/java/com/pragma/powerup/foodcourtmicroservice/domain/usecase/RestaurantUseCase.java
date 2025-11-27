package com.pragma.powerup.foodcourtmicroservice.domain.usecase;

import com.pragma.powerup.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IUserClientPort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.UserModel;
import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserClientPort userClientPort;

    public RestaurantUseCase(
        IRestaurantPersistencePort restaurantPersistencePort,
        IUserClientPort userClientPort
    ) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userClientPort = userClientPort;
    }

    @Override
    public RestaurantModel createRestaurant(RestaurantModel restaurantModel) {
        validateRestaurant(restaurantModel);
        validateOwner(restaurantModel.getOwnerId());
        if (restaurantPersistencePort.existsByNameOrNit(restaurantModel.getName(), restaurantModel.getNit())) {
            throw new IllegalArgumentException("Restaurant already exists with the same name or NIT");
        }
        return restaurantPersistencePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantModel> getRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    private void validateRestaurant(RestaurantModel restaurantModel) {
        if (restaurantModel == null) {
            throw new IllegalArgumentException("Restaurant information is required");
        }
        if (isBlank(restaurantModel.getName())) {
            throw new IllegalArgumentException("Restaurant name is required");
        }
        if (isBlank(restaurantModel.getUrlLogo())) {
            throw new IllegalArgumentException("Restaurant logo URL is required");
        }
        if (restaurantModel.getOwnerId() == null) {
            throw new IllegalArgumentException("Owner id is required");
        }
    }

    private void validateOwner(Long ownerId) {
        UserModel user = userClientPort.getUserById(ownerId);
        if (user == null) {
            throw new IllegalArgumentException("Owner not found");
        }
        // Assuming "PROPIETARIO" is the role name required
        if (!"PROPIETARIO".equalsIgnoreCase(user.getRole()) && !"ROLE_OWNER".equalsIgnoreCase(user.getRole())) {
             throw new IllegalArgumentException("User must be an owner (PROPIETARIO) to create a restaurant");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
