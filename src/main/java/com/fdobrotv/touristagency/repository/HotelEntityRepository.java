package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.HotelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface HotelEntityRepository extends PagingAndSortingRepository<HotelEntity, UUID>,CrudRepository<HotelEntity, UUID> {
}