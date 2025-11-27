package com.pragma.powerup.foodcourtmicroservice.application.mapper;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDishRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    DishModel toModel(DishRequest dishRequest);
}
