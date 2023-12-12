package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.CarModel;
import com.fdobrotv.touristagency.dto.CarModelIn;
import com.fdobrotv.touristagency.dto.CarModel;
import com.fdobrotv.touristagency.entity.CarModelEntity;
import com.fdobrotv.touristagency.entity.CarModelEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CarModelMapper {
    public static CarModel toDTO(CarModelEntity carModelEntity) {
        CarModel carModel = new CarModel();
        carModel.id(carModelEntity.getId());
        carModel.name(carModelEntity.getName());
        return carModel;
    }

    public static List<CarModel> toDTO(List<CarModelEntity> CarModelEntities) {
        return CarModelEntities.stream()
                .map(CarModelMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<CarModel> toDTO(Iterable<CarModelEntity> CarModelEntities) {
        Stream<CarModelEntity> targetStream = StreamSupport.stream(CarModelEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static CarModelEntity toEntity(CarModelIn CarModelIn) {
        CarModelEntity CarModelEntity = new CarModelEntity();
        CarModelEntity.setName(CarModelIn.getName());
        return CarModelEntity;
    }
}
