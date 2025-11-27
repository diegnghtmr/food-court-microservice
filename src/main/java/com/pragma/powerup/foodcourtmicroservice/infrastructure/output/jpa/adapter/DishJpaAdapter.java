package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.adapter;

import com.pragma.powerup.foodcourtmicroservice.domain.model.CategoryModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.DishModel;
import com.pragma.powerup.foodcourtmicroservice.domain.model.RestaurantModel;
import com.pragma.powerup.foodcourtmicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.DishEntity;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.IDishRepository;
import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public DishModel saveDish(DishModel dishModel) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dishModel);
        
        // Ensure relationships are set correctly if MapStruct only sets IDs on a proxy or similar.
        // Depending on JPA provider, setting ID might be enough, but safer to fetch references if needed
        // or rely on EntityManager.getReference.
        // However, with Spring Data JPA, if we save an entity with a child having just an ID, 
        // it might try to persist a new one or fail if not attached.
        // Best practice: fetch the entities to ensure existence and attachment, 
        // although the usecase already checked for existence.
        // MapStruct mapping `restaurant.id` -> `restaurantId` might create a new RestaurantEntity with just ID.
        // Let's trust MapStruct + JPA to handle the foreign key by ID if properly configured, 
        // OR we manually set the entities to be sure.
        
        // Since the usecase already validated existence, we can getReferenceById (lazy) or findById.
        RestaurantEntity restaurant = restaurantRepository.findById(dishModel.getRestaurantId()).orElse(null);
        CategoryEntity category = categoryRepository.findById(dishModel.getCategoryId()).orElse(null);
        
        dishEntity.setRestaurant(restaurant);
        dishEntity.setCategory(category);
        
        DishEntity savedDish = dishRepository.save(dishEntity);
        return dishEntityMapper.toModel(savedDish);
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishRepository.findById(id)
                .map(dishEntityMapper::toModel)
                .orElse(null);
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .map(restaurantEntityMapper::toModel)
                .orElse(null);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
         return categoryRepository.findById(id)
                .map(categoryEntityMapper::toModel)
                .orElse(null);
    }
}
