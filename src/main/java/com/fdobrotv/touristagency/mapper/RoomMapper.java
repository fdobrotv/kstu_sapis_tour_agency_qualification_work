package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.*;
import com.fdobrotv.touristagency.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RoomMapper {
    public static Room toDTO(RoomEntity roomEntity) {
        Room room = new Room();
        room.id(roomEntity.getId());
        room.name(roomEntity.getName());
        room.serviceClass(ServiceClass.fromValue(roomEntity.getServiceClass().name()));
        room.pricePerNight(new BigDecimal(roomEntity.getPricePerNight()));
        room.hotel(HotelMapper.toDTO(roomEntity.getHotel()));
        return room;
    }

    public static List<Room> toDTO(List<RoomEntity> roomEntities) {
        return roomEntities.stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Room> toDTO(Iterable<RoomEntity> roomEntities) {
        Stream<RoomEntity> targetStream = StreamSupport.stream(roomEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static RoomEntity toEntity(RoomIn roomIn,
                                      HotelEntity hotelEntity) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setHotel(hotelEntity);
        com.fdobrotv.touristagency.entity.enums.ServiceClass roomClass =
                com.fdobrotv.touristagency.entity.enums.ServiceClass.fromValue(roomIn.getServiceClass().name());
        roomEntity.setServiceClass(roomClass);
        roomEntity.setName(roomIn.getName());
        roomEntity.setPricePerNight(roomIn.getPricePerNight().toBigInteger());

        return roomEntity;
    }
}
