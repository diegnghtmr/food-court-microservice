package com.pragma.powerup.foodcourtmicroservice.domain.spi;

import com.pragma.powerup.foodcourtmicroservice.domain.model.UserModel;

public interface IUserClientPort {
    UserModel getUserById(Long id);
}
