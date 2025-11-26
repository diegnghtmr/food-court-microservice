package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper;

import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.RestaurantEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRestaurantEntityMapper {
    RestaurantEntity toEntity(RestaurantModel restaurantModel);

    RestaurantModel toModel(RestaurantEntity restaurantEntity);

    List<RestaurantModel> toModelList(List<RestaurantEntity> restaurantEntities);
}
