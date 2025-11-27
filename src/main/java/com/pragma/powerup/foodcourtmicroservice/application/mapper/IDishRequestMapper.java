package com.pragma.powerup.foodcourtmicroservice.application.mapper;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishUpdateRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDishRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    DishModel toModel(DishRequest dishRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "urlImage", ignore = true)
    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "active", ignore = true)
    DishModel toModel(DishUpdateRequest dishUpdateRequest);
}
