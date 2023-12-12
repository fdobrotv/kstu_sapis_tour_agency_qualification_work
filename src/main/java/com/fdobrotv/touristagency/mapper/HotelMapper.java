package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.*;
import com.fdobrotv.touristagency.dto.Hotel;
import com.fdobrotv.touristagency.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class HotelMapper {
    public static Hotel toDTO(HotelEntity hotelEntity) {
        Hotel hotel = new Hotel();
        hotel.id(hotelEntity.getId());
        hotel.name(hotelEntity.getName());
        hotel.address(hotelEntity.getAddress());
        hotel.serviceClass(ServiceClass.fromValue(hotelEntity.getServiceClass().name()));
        hotel.isGuideIncluded(hotelEntity.getIsGuideIncluded());
        return hotel;
    }

    public static List<Hotel> toDTO(List<HotelEntity> hotelEntities) {
        return hotelEntities.stream()
                .map(HotelMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Hotel> toDTO(Iterable<HotelEntity> hotelEntities) {
        Stream<HotelEntity> targetStream = StreamSupport.stream(hotelEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static HotelEntity toEntity(HotelIn hotelIn) {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setName(hotelIn.getName());
        hotelEntity.setAddress(hotelIn.getAddress());
        hotelEntity.setIsGuideIncluded(hotelIn.getIsGuideIncluded());
        hotelEntity.setServiceClass(ServiceClassMapper.toEntity(hotelIn.getServiceClass()));
        return hotelEntity;
    }
}
