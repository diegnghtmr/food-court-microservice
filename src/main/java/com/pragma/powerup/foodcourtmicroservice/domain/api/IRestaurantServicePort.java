package com.pragma.powerup.foodcourtmicroservice.domain.api;

import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import java.util.List;

public interface IRestaurantServicePort {
    RestaurantModel createRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getRestaurants();
}
