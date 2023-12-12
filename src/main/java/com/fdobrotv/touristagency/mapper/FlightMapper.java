package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.*;
import com.fdobrotv.touristagency.entity.FlightEntity;
import com.fdobrotv.touristagency.entity.FlightEntity;
import com.fdobrotv.touristagency.entity.TourEntity;
import lombok.extern.java.Log;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.time.ZoneOffset.UTC;

@Log
public class FlightMapper {
    public static Flight toDTO(FlightEntity flightEntity) {
        Flight flight = new Flight();
        UUID id = flightEntity.getId();
        log.info("id: " + id);
        flight.id(id);
        flight.departureAirport(flightEntity.getDepartureAirport());
        flight.arrivalAirport(flightEntity.getArrivalAirport());
        OffsetDateTime departureDateTime = flightEntity.getDepartureDateTime();
        log.info("departureDateTime: " + departureDateTime);
        flight.departureDateTime(departureDateTime);
        flight.arrivalDateTime(flightEntity.getArrivalDateTime());
        return flight;
    }

    public static List<Flight> toDTO(List<FlightEntity> flightEntities) {
        return flightEntities.stream()
                .map(FlightMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Flight> toDTO(Iterable<FlightEntity> flightEntities) {
        Stream<FlightEntity> targetStream = StreamSupport.stream(flightEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static FlightEntity toEntity(FlightIn flightIn) {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setDepartureAirport(flightIn.getDepartureAirport());
        flightEntity.setArrivalAirport(flightIn.getArrivalAirport());
        OffsetDateTime departureDateTime = flightIn.getDepartureDateTime();
        log.info("departureDateTime: " + departureDateTime);
        flightEntity.setDepartureDateTime(departureDateTime);
        flightEntity.setArrivalDateTime(flightIn.getArrivalDateTime());
        return flightEntity;
    }
}
