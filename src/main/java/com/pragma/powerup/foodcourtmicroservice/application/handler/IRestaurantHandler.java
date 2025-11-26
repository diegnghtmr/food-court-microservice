package com.pragma.powerup.foodcourtmicroservice.application.handler;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.RestaurantRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.RestaurantResponse;
import java.util.List;

public interface IRestaurantHandler {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest);

    List<RestaurantResponse> getRestaurants();
}
