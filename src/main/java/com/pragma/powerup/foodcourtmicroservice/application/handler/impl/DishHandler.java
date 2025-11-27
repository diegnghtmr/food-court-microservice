package com.pragma.powerup.foodcourtmicroservice.application.handler.impl;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishRequest;
import com.pragma.powerup.foodcourtmicroservice.application.dto.response.DishResponse;
import com.pragma.powerup.foodcourtmicroservice.application.handler.IDishHandler;
import com.pragma.powerup.foodcourtmicroservice.application.mapper.IDishRequestMapper;
import com.pragma.powerup.foodcourtmicroservice.application.mapper.IDishResponseMapper;
import com.pragma.powerup.foodcourtmicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.powerup.foodcourtmicroservice.application.dto.request.DishUpdateRequest;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public DishResponse createDish(DishRequest dishRequest) {
        DishModel dishModel = dishRequestMapper.toModel(dishRequest);
        DishModel createdDish = dishServicePort.createDish(dishModel, dishRequest.getOwnerId());
        return dishResponseMapper.toResponse(createdDish);
    }

    @Override
    public DishResponse updateDish(Long id, DishUpdateRequest dishUpdateRequest) {
        DishModel dishModel = dishRequestMapper.toModel(dishUpdateRequest);
        DishModel updatedDish = dishServicePort.updateDish(id, dishModel, dishUpdateRequest.getOwnerId());
        return dishResponseMapper.toResponse(updatedDish);
    }

    @Override
    public DishResponse updateDishActiveState(Long id, boolean isActive) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        
        DishModel updatedDish = dishServicePort.updateDishActiveState(id, isActive, userId);
        return dishResponseMapper.toResponse(updatedDish);
    }

    @Override
    public List<DishResponse> getDishesByRestaurant(Long restaurantId, Integer page, Integer size) {
        return dishServicePort.getDishesByRestaurant(restaurantId, page, size).stream()
                .map(dishResponseMapper::toResponse)
                .toList();
    }
}
