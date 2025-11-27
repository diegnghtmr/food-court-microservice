package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper;

import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDishEntityMapper {
    @Mapping(target = "restaurant.id", source = "restaurantId")
    @Mapping(target = "category.id", source = "categoryId")
    DishEntity toEntity(DishModel dishModel);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "categoryId", source = "category.id")
    DishModel toModel(DishEntity dishEntity);
}
