package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Page<DishEntity> findByRestaurantIdAndActive(Long restaurantId, boolean active, Pageable pageable);
}
