package com.pragma.powerup.foodcourtmicroservice.application.handler.impl;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.RestaurantRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.RestaurantResponse;
import com.pragma.powerup.foodcourtmicroservice.application.handler.IRestaurantHandler;
import com.pragma.powerup.foodcourtmicroservice.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.foodcourtmicroservice.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toModel(restaurantRequest);
        RestaurantModel createdRestaurant = restaurantServicePort.createRestaurant(restaurantModel);
        return restaurantResponseMapper.toResponse(createdRestaurant);
    }

    @Override
    public List<RestaurantResponse> getRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getRestaurants());
    }

    @Override
    public List<RestaurantResponse> getRestaurants(Integer page, Integer size) {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getRestaurants(page, size));
    }

    @Override
    public RestaurantResponse getRestaurant(Long id) {
        RestaurantModel model = restaurantServicePort.getRestaurant(id);
        return model != null ? restaurantResponseMapper.toResponse(model) : null;
    }
}
