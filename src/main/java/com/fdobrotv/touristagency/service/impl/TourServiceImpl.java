package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Tour;
import com.fdobrotv.touristagency.dto.TourIn;
import com.fdobrotv.touristagency.entity.*;
import com.fdobrotv.touristagency.mapper.TourMapper;
import com.fdobrotv.touristagency.repository.*;
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
public class TourServiceImpl implements CRUDService<Tour, TourIn> {

    @Autowired
    public TourEntityRepository tourEntityRepository;

    @Autowired
    public RoomEntityRepository roomEntityRepository;

    @Autowired
    public TransferEntityRepository transferEntityRepository;

    @Autowired
    public FlightEntityRepository flightEntityRepository;

    @Autowired
    public FoodOptionEntityRepository foodOptionEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Tour getById(UUID id) {
        Optional<TourEntity> tourByID = tourEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Tour by id");
        return TourMapper.toDTO(tourByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<TourEntity> TourEntityOptional = tourEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Tour by id");
        TourEntity tourEntity = TourEntityOptional.orElseThrow(unableToFindResource);
        tourEntityRepository.delete(tourEntity);
    }

    @Override
    public Tour create(TourIn tourIn) {
        TourEntity tourEntity = TourMapper.toEntity(
                tourIn,
                getRoomEntity(tourIn.getRoomId()),
                getFlightEntity(tourIn.getDepartureFlightId()),
                getFlightEntity(tourIn.getArrivalFlightId()),
                getTransferEntity(tourIn.getTransferToHotelId()),
                getTransferEntity(tourIn.getTransferFromHotelId()),
                getFoodOptionEntity(tourIn.getSelectedFoodOptionId())
        );
        TourEntity saved = tourEntityRepository.save(tourEntity);
        return TourMapper.toDTO(saved);
    }

    private RoomEntity getRoomEntity(UUID roomId) {
        Optional<RoomEntity> flightEntityOptional = roomEntityRepository.findById(roomId);
        Supplier<ResponseStatusException> unableToFindRoomResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Room by id");
        return flightEntityOptional.orElseThrow(unableToFindRoomResource);
    }

    private FlightEntity getFlightEntity(UUID flightId) {
        Optional<FlightEntity> flightEntityOptional = flightEntityRepository.findById(flightId);
        Supplier<ResponseStatusException> unableToFindFlightResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Flight by id");
        return flightEntityOptional.orElseThrow(unableToFindFlightResource);
    }

    private TransferEntity getTransferEntity(UUID transferId) {
        Optional<TransferEntity> transferEntityOptional = transferEntityRepository.findById(transferId);
        Supplier<ResponseStatusException> unableToFindTransferResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Transfer by id");
        return transferEntityOptional.orElseThrow(unableToFindTransferResource);
    }

    private FoodOptionEntity getFoodOptionEntity(UUID foodOptionId) {
        Optional<FoodOptionEntity> foodOptionEntityOptional = foodOptionEntityRepository.findById(foodOptionId);
        Supplier<ResponseStatusException> unableToFindFoodOptionResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Food Option by id");
        return foodOptionEntityOptional.orElseThrow(unableToFindFoodOptionResource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getList(Pageable pageable) {
        Iterable<TourEntity> all = tourEntityRepository.findAll(pageable);
        return TourMapper.toDTO(all);
    }
}
