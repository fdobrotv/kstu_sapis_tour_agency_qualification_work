package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.FoodOptionIn;
import com.fdobrotv.touristagency.entity.FoodOptionEntity;
import com.fdobrotv.touristagency.mapper.FoodOptionMapper;
import com.fdobrotv.touristagency.repository.FoodOptionEntityRepository;
import com.fdobrotv.touristagency.service.CRUDService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class FoodOptionServiceImpl implements CRUDService<FoodOption, FoodOptionIn> {

    @Autowired
    public FoodOptionEntityRepository foodOptionEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public FoodOption getById(UUID id) {
        Optional<FoodOptionEntity> foodOptionByID = foodOptionEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find FoodOption by id");
        return FoodOptionMapper.toDTO(foodOptionByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<FoodOptionEntity> FoodOptionEntityOptional = foodOptionEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete FoodOption by id");
        FoodOptionEntity FoodOptionEntity = FoodOptionEntityOptional.orElseThrow(unableToFindResource);
        foodOptionEntityRepository.delete(FoodOptionEntity);
    }

    @Override
    public FoodOption create(FoodOptionIn foodOptionIn) {
        FoodOptionEntity foodOptionEntity = FoodOptionMapper.toEntity(foodOptionIn);
        FoodOptionEntity saved = foodOptionEntityRepository.save(foodOptionEntity);
        return FoodOptionMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodOption> getList(Pageable pageable) {
        Iterable<FoodOptionEntity> all = foodOptionEntityRepository.findAll(pageable);
        return FoodOptionMapper.toDTO(all);
    }
}
