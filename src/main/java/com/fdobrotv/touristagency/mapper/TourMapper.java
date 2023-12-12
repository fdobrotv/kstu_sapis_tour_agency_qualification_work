package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.Tour;
import com.fdobrotv.touristagency.dto.TourIn;
import com.fdobrotv.touristagency.dto.Tour;
import com.fdobrotv.touristagency.entity.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TourMapper {
    public static Tour toDTO(TourEntity tourEntity) {
        Tour tour = new Tour();
        tour.id(tourEntity.getId());
        tour.departureFlight(FlightMapper.toDTO(tourEntity.getDepartureFlight()));
        tour.arrivalFlight(FlightMapper.toDTO(tourEntity.getArrivalFlight()));
        tour.transferToHotel(TransferMapper.toDTO(tourEntity.getTransferToHotel()));
        tour.transferFromHotel(TransferMapper.toDTO(tourEntity.getTransferFromHotel()));
        tour.description(tourEntity.getDescription());
        tour.price(new BigDecimal(tourEntity.getPrice()));
        tour.room(RoomMapper.toDTO(tourEntity.getRoom()));
        tour.selectedFoodOption(FoodOptionMapper.toDTO(tourEntity.getFoodOption()));
        return tour;
    }

    public static List<Tour> toDTO(List<TourEntity> tourEntities) {
        return tourEntities.stream()
                .map(TourMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Tour> toDTO(Iterable<TourEntity> tourEntities) {
        Stream<TourEntity> targetStream = StreamSupport.stream(tourEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static TourEntity toEntity(TourIn tourIn,
                                      RoomEntity roomEntity,
                                      FlightEntity departureFlight,
                                      FlightEntity arrivalFlight,
                                      TransferEntity transferToHotelEntity,
                                      TransferEntity transferFromHotelEntity,
                                      FoodOptionEntity foodOptionEntity) {
        TourEntity tourEntity = new TourEntity();
        tourEntity.setRoom(roomEntity);
        tourEntity.setPrice(tourIn.getPrice().toBigInteger());
        tourEntity.setDepartureFlight(departureFlight);
        tourEntity.setArrivalFlight(arrivalFlight);
        tourEntity.setDescription(tourIn.getDescription());
        tourEntity.setTransferToHotel(transferToHotelEntity);
        tourEntity.setTransferFromHotel(transferFromHotelEntity);
        tourEntity.setFoodOption(foodOptionEntity);

        return tourEntity;
    }
}
