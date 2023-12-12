package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.FlightEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface FlightEntityRepository extends PagingAndSortingRepository<FlightEntity, UUID>, CrudRepository<FlightEntity, UUID> {
}