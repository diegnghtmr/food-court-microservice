package com.pragma.powerup.foodcourtmicroservice.application.mapper;

import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IDishResponseMapper {
    DishResponse toResponse(DishModel dishModel);
}
