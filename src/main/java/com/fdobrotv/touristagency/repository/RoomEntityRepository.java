package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RoomEntityRepository extends PagingAndSortingRepository<RoomEntity, UUID>, CrudRepository<RoomEntity, UUID> {
}