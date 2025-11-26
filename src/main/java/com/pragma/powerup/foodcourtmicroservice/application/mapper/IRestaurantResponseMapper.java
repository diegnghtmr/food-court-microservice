package com.pragma.powerup.foodcourtmicroservice.application.mapper;

import com.pragma.powerup.foodcourtmicroservice.application.dto.response.RestaurantResponse;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantResponseMapper {
    RestaurantResponse toResponse(RestaurantModel restaurantModel);

    List<RestaurantResponse> toResponseList(List<RestaurantModel> restaurantModels);
}
