package com.pragma.powerup.foodcourtmicroservice.infrastructure.configuration;

import com.pragma.powerup.foodcourtmicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IUserClientPort;
import com.pragma.powerup.foodcourtmicroservice.domain.usecase.DishUseCase;
import com.pragma.powerup.foodcourtmicroservice.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.IDishRepository;
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
    
    private final IDishRepository dishRepository;
    private final ICategoryRepository categoryRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), userClientPort);
    }
    
    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, restaurantRepository, categoryRepository, dishEntityMapper, restaurantEntityMapper, categoryEntityMapper);
    }
    
    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
    }
}
