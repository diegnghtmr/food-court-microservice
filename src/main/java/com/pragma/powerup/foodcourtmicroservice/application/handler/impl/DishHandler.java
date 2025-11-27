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
}
