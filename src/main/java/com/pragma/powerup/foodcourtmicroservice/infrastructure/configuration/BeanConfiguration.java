package com.pragma.powerup.foodcourtmicroservice.infrastructure.configuration;

import com.pragma.powerup.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IUserClientPort;
import com.pragma.powerup.foodcourtmicroservice.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IUserClientPort userClientPort;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userClientPort);
    }
}
