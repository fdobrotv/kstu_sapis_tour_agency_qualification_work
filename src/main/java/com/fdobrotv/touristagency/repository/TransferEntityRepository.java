package com.fdobrotv.touristagency.repository;

import com.fdobrotv.touristagency.entity.TransferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TransferEntityRepository extends PagingAndSortingRepository<TransferEntity, UUID>, CrudRepository<TransferEntity, UUID> {
}