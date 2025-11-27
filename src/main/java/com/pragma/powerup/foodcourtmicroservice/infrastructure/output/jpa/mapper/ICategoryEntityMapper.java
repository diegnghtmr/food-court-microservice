package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper;

import com.pragma.powerup.foodcourtmicroservice.domain.model.CategoryModel;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(CategoryModel categoryModel);
    CategoryModel toModel(CategoryEntity categoryEntity);
}
