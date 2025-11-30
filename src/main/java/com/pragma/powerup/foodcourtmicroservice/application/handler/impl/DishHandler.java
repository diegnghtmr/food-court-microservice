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
        Long ownerId = getCurrentUserId();
        if (dishRequest.getCategoryId() == null && dishRequest.getCategoryKey() != null) {
            dishRequest.setCategoryId(resolveCategoryId(dishRequest.getCategoryKey()));
        }
        DishModel dishModel = dishRequestMapper.toModel(dishRequest);
        DishModel createdDish = dishServicePort.createDish(dishModel, ownerId);
        return dishResponseMapper.toResponse(createdDish);
    }

    @Override
    public DishResponse updateDish(Long id, DishUpdateRequest dishUpdateRequest) {
        DishModel dishModel = dishRequestMapper.toModel(dishUpdateRequest);
        DishModel updatedDish = dishServicePort.updateDish(id, dishModel, getCurrentUserId());
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

    @Override
    public DishResponse getDish(Long id) {
        DishModel dish = dishServicePort.getDish(id);
        return dish != null ? dishResponseMapper.toResponse(dish) : null;
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails details) {
            return details.getId();
        }
        throw new IllegalStateException("User not authenticated");
    }

    private Long resolveCategoryId(String categoryKey) {
        return switch (categoryKey.toUpperCase()) {
            case "ENTRADA" -> 1L;
            case "PLATO_FUERTE" -> 2L;
            case "POSTRE" -> 3L;
            case "BEBIDA" -> 4L;
            default -> null;
        };
    }
}
