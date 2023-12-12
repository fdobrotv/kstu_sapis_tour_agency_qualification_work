package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Room;
import com.fdobrotv.touristagency.dto.RoomIn;
import com.fdobrotv.touristagency.entity.HotelEntity;
import com.fdobrotv.touristagency.entity.RoomEntity;
import com.fdobrotv.touristagency.mapper.RoomMapper;
import com.fdobrotv.touristagency.repository.HotelEntityRepository;
import com.fdobrotv.touristagency.repository.RoomEntityRepository;
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
public class RoomServiceImpl implements CRUDService<Room, RoomIn> {

    @Autowired
    public RoomEntityRepository roomEntityRepository;

    @Autowired
    public HotelEntityRepository hotelEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Room getById(UUID id) {
        Optional<RoomEntity> roomByID = roomEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Room by id");
        return RoomMapper.toDTO(roomByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<RoomEntity> RoomEntityOptional = roomEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Room by id");
        RoomEntity roomEntity = RoomEntityOptional.orElseThrow(unableToFindResource);
        roomEntityRepository.delete(roomEntity);
    }

    @Override
    public Room create(RoomIn roomIn) {
        RoomEntity roomEntity = RoomMapper.toEntity(
                roomIn,
                getHotelEntity(roomIn.getHotelId())
        );
        RoomEntity saved = roomEntityRepository.save(roomEntity);
        return RoomMapper.toDTO(saved);
    }

    private HotelEntity getHotelEntity(UUID roomId) {
        Optional<HotelEntity> hotelEntityOptional = hotelEntityRepository.findById(roomId);
        Supplier<ResponseStatusException> unableToFindHotelResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Hotel by id");
        return hotelEntityOptional.orElseThrow(unableToFindHotelResource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getList(Pageable pageable) {
        Iterable<RoomEntity> all = roomEntityRepository.findAll(pageable);
        return RoomMapper.toDTO(all);
    }
}
