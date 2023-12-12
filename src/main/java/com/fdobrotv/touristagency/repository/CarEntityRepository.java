package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.CarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CarEntityRepository extends PagingAndSortingRepository<CarEntity, UUID>, CrudRepository<CarEntity, UUID> {
}