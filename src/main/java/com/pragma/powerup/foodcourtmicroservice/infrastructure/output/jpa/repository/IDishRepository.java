package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity, Long> {
}
