package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Car;
import com.fdobrotv.touristagency.dto.CarIn;
import com.fdobrotv.touristagency.entity.CarEntity;
import com.fdobrotv.touristagency.entity.CarMarkEntity;
import com.fdobrotv.touristagency.entity.CarModelEntity;
import com.fdobrotv.touristagency.mapper.CarMapper;
import com.fdobrotv.touristagency.repository.CarEntityRepository;
import com.fdobrotv.touristagency.repository.CarMarkEntityRepository;
import com.fdobrotv.touristagency.repository.CarModelEntityRepository;
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
public class CarServiceImpl implements CRUDService<Car, CarIn> {

    @Autowired
    public CarEntityRepository carEntityRepository;

    @Autowired
    public CarMarkEntityRepository carMarkEntityRepository;

    @Autowired
    public CarModelEntityRepository carModelEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Car getById(UUID id) {
        Optional<CarEntity> carByID = carEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Car by id");
        return CarMapper.toDTO(carByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<CarEntity> CarEntityOptional = carEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Car by id");
        CarEntity CarEntity = CarEntityOptional.orElseThrow(unableToFindResource);
        carEntityRepository.delete(CarEntity);
    }

    @Override
    public Car create(CarIn carIn) {
        CarMarkEntity carMarkEntity = getCarMarkEntity(carIn);
        CarModelEntity carModelEntity = getCarModelEntity(carIn);
        CarEntity carEntity = CarMapper.toEntity(
                carIn,
                carMarkEntity,
                carModelEntity
        );
        CarEntity saved = carEntityRepository.save(carEntity);
        return CarMapper.toDTO(saved);
    }

    private CarMarkEntity getCarMarkEntity(CarIn carIn) {
        Optional<CarMarkEntity> carMarkEntityOptional = carMarkEntityRepository.findById(carIn.getMarkId());
        Supplier<ResponseStatusException> unableToFindCarMarkResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Car Mark by id");
        return carMarkEntityOptional.orElseThrow(unableToFindCarMarkResource);
    }

    private CarModelEntity getCarModelEntity(CarIn carIn) {
        Optional<CarModelEntity> carModelEntityOptional = carModelEntityRepository.findById(carIn.getModelId());
        Supplier<ResponseStatusException> unableToFindCarModelResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Car Model by id");
        return carModelEntityOptional.orElseThrow(unableToFindCarModelResource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getList(Pageable pageable) {
        Iterable<CarEntity> all = carEntityRepository.findAll(pageable);
        return CarMapper.toDTO(all);
    }
}
