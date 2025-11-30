package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.adapter;

import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurantModel);
        RestaurantEntity savedRestaurant = restaurantRepository.save(restaurantEntity);
        return restaurantEntityMapper.toModel(savedRestaurant);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        return restaurantEntityMapper.toModelList(restaurantRepository.findAll());
    }

    @Override
    public List<RestaurantModel> getAllRestaurants(Integer page, Integer size) {
        return restaurantEntityMapper.toModelList(
            restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("name").ascending())).getContent()
        );
    }

    @Override
    public boolean existsByNameOrNit(String name, String nit) {
        return restaurantRepository.existsByNameIgnoreCaseOrNit(name, nit);
    }

    @Override
    public RestaurantModel getById(Long id) {
        return restaurantRepository.findById(id).map(restaurantEntityMapper::toModel).orElse(null);
    }
}
