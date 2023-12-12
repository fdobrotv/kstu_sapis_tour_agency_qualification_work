package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.TourEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TourEntityRepository extends PagingAndSortingRepository<TourEntity, UUID>, CrudRepository<TourEntity, UUID> {
}