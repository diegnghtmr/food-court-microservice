package com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.repository;

import com.pragma.powerup.foodcourtmicroservice.infrastructure.output.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
