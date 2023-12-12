package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.FoodOptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface FoodOptionEntityRepository extends PagingAndSortingRepository<FoodOptionEntity, UUID>,CrudRepository<FoodOptionEntity, UUID> {
}