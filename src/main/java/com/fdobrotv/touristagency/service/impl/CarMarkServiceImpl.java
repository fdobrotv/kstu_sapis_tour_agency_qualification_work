package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.CarMark;
import com.fdobrotv.touristagency.dto.CarMarkIn;
import com.fdobrotv.touristagency.entity.CarMarkEntity;
import com.fdobrotv.touristagency.mapper.CarMarkMapper;
import com.fdobrotv.touristagency.repository.CarMarkEntityRepository;
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
public class CarMarkServiceImpl implements CRUDService<CarMark, CarMarkIn> {

    @Autowired
    public CarMarkEntityRepository carMarkEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public CarMark getById(UUID id) {
        Optional<CarMarkEntity> carMarkEntity = carMarkEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Car Mark by id");
        return CarMarkMapper.toDTO(carMarkEntity.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<CarMarkEntity> carMarkEntityOptional = carMarkEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Car Mark by id");
        CarMarkEntity carMarkEntity = carMarkEntityOptional.orElseThrow(unableToFindResource);
        carMarkEntityRepository.delete(carMarkEntity);
    }

    @Override
    public CarMark create(CarMarkIn carMarkIn) {
        CarMarkEntity carMarkEntity = CarMarkMapper.toEntity(carMarkIn);
        CarMarkEntity saved = carMarkEntityRepository.save(carMarkEntity);
        return CarMarkMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarMark> getList(Pageable pageable) {
        Iterable<CarMarkEntity> all = carMarkEntityRepository.findAll(pageable);
        return CarMarkMapper.toDTO(all);
    }
}
