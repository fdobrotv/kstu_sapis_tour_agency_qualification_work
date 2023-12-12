package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.Car;
import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.FoodOptionIn;
import com.fdobrotv.touristagency.entity.CarEntity;
import com.fdobrotv.touristagency.entity.FoodOptionEntity;
import com.fdobrotv.touristagency.entity.HotelEntity;
import com.fdobrotv.touristagency.entity.FoodOptionEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FoodOptionMapper {
    public static FoodOption toDTO(FoodOptionEntity foodOptionEntity) {
        FoodOption foodOption = new FoodOption();
        foodOption.id(foodOptionEntity.getId());
        foodOption.name(foodOptionEntity.getName());
        foodOption.price(new BigDecimal(foodOptionEntity.getPrice()));
        return foodOption;
    }

    public static List<FoodOption> toDTO(List<FoodOptionEntity> foodOptionEntities) {
        return foodOptionEntities.stream()
                .map(FoodOptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<FoodOption> toDTO(Iterable<FoodOptionEntity> foodOptionEntities) {
        Stream<FoodOptionEntity> targetStream = StreamSupport.stream(foodOptionEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static FoodOptionEntity toEntity(FoodOptionIn foodOptionIn) {
        FoodOptionEntity foodOptionEntity = new FoodOptionEntity();
        foodOptionEntity.setName(foodOptionIn.getName());
        foodOptionEntity.setPrice(foodOptionIn.getPrice().toBigInteger());

        return foodOptionEntity;
    }
}
