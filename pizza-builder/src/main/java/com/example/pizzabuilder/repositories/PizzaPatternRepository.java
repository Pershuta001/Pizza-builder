package com.example.pizzabuilder.repositories;

import com.example.pizzabuilder.model.PizzaPattern;
import com.example.pizzabuilder.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PizzaPatternRepository extends JpaRepository<PizzaPattern, UUID> {

    Optional<PizzaPattern> getByName(String name);

    List<PizzaPattern> findByConfirmed(Boolean confirmed);

    List<PizzaPattern> getAllByUserEntityUUID(UserEntity uuid);
}
