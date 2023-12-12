package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.CarMarkEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CarMarkEntityRepository extends PagingAndSortingRepository<CarMarkEntity, UUID>, CrudRepository<CarMarkEntity, UUID> {
}