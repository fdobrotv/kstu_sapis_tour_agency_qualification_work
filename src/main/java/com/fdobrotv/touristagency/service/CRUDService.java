package com.fdobrotv.touristagency.service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CRUDService<T, Y> {
    T getById(UUID id);
    void deleteById(UUID id);
    T create(Y dtoIn);
    List<T> getList(Pageable pageable);
}
