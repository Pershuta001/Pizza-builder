package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.Ingredient;
import com.example.pizzabuilder.model.Order;
import com.example.pizzabuilder.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUuid(UUID uuid);
    List<UserEntity> findByRoleId(Integer roleId);
}
