package com.pragma.powerup.foodcourtmicroservice.domain.spi;

import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();

    boolean existsByNameOrNit(String name, String nit);
}
