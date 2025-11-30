package com.pragma.powerup.foodcourtmicroservice.domain.spi;

import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();

    List<RestaurantModel> getAllRestaurants(Integer page, Integer size);

    boolean existsByNameOrNit(String name, String nit);

    RestaurantModel getById(Long id);
}
