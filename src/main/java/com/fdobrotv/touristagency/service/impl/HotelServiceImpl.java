package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Hotel;
import com.fdobrotv.touristagency.dto.HotelIn;
import com.fdobrotv.touristagency.entity.HotelEntity;
import com.fdobrotv.touristagency.mapper.HotelMapper;
import com.fdobrotv.touristagency.repository.HotelEntityRepository;
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
public class HotelServiceImpl implements CRUDService<Hotel, HotelIn> {

    @Autowired
    public HotelEntityRepository hotelEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Hotel getById(UUID id) {
        Optional<HotelEntity> hotelByID = hotelEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Hotel by id");
        return HotelMapper.toDTO(hotelByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<HotelEntity> HotelEntityOptional = hotelEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Hotel by id");
        HotelEntity HotelEntity = HotelEntityOptional.orElseThrow(unableToFindResource);
        hotelEntityRepository.delete(HotelEntity);
    }

    @Override
    public Hotel create(HotelIn hotelIn) {
        HotelEntity hotelEntity = HotelMapper.toEntity(
                hotelIn
        );
        HotelEntity saved = hotelEntityRepository.save(hotelEntity);
        return HotelMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Hotel> getList(Pageable pageable) {
        Iterable<HotelEntity> all = hotelEntityRepository.findAll(pageable);
        return HotelMapper.toDTO(all);
    }
}
