package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.adapter;

import com.pragma.powerup.foodcourtmicroservice.domain.model.UserModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IUserClientPort;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.client.UserClient;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.feign.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFeignAdapter implements IUserClientPort {

    private final UserClient userClient;

    @Override
    public UserModel getUserById(Long id) {
        UserResponse response = userClient.getUserById(id);
        if (response == null) {
            return null;
        }
        return new UserModel(response.getId(), response.getEmail(), response.getRole());
    }

    @Override
    public boolean validateOwnerRole(Long userId) {
        Boolean hasOwnerRole = userClient.hasOwnerRole(userId);
        return Boolean.TRUE.equals(hasOwnerRole);
    }
}
