package com.pragma.powerup.foodcourtmicroservice.application.mapper;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.RestaurantRequest;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IRestaurantRequestMapper {
    @Mapping(target = "id", ignore = true)
    RestaurantModel toModel(RestaurantRequest restaurantRequest);
}
