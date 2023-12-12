package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.Car;
import com.fdobrotv.touristagency.dto.CarIn;
import com.fdobrotv.touristagency.dto.CarMark;
import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.entity.CarEntity;
import com.fdobrotv.touristagency.entity.CarMarkEntity;
import com.fdobrotv.touristagency.entity.CarModelEntity;
import com.fdobrotv.touristagency.entity.FlightEntity;

import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CarMapper {
    public static Car toDTO(CarEntity carEntity) {
        Car car = new Car();
        car.id(carEntity.getId());
        car.mark(carEntity.getMark().getName());
        car.model(carEntity.getModel().getName());
        car.plateNumber(carEntity.getPlateNumber());
        car.color(CarColorMapper.toDTO(carEntity.getColor()));
        return car;
    }

    public static List<Car> toDTO(List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(CarMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Car> toDTO(Iterable<CarEntity> carEntities) {
        Stream<CarEntity> targetStream = StreamSupport.stream(carEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static CarEntity toEntity(CarIn carIn, CarMarkEntity carMarkEntity, CarModelEntity carModelEntity) {
        CarEntity carEntity = new CarEntity();
        carEntity.setPlateNumber(carIn.getPlateNumber());
        carEntity.setColor(CarColorMapper.toEntity(carIn.getColor()));
        carEntity.setMark(carMarkEntity);
        carEntity.setModel(carModelEntity);
        return carEntity;
    }
}
