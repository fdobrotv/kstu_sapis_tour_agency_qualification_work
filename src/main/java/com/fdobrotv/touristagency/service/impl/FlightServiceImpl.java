package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.dto.FlightIn;
import com.fdobrotv.touristagency.entity.FlightEntity;
import com.fdobrotv.touristagency.mapper.FlightMapper;
import com.fdobrotv.touristagency.repository.FlightEntityRepository;
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
public class FlightServiceImpl implements CRUDService<Flight, FlightIn> {

    @Autowired
    public FlightEntityRepository flightEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Flight getById(UUID id) {
        Optional<FlightEntity> flightEntityOptional = flightEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Flight Mark by id");
        return FlightMapper.toDTO(flightEntityOptional.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<FlightEntity> flightEntityOptional = flightEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Flight Mark by id");
        FlightEntity flightEntity = flightEntityOptional.orElseThrow(unableToFindResource);
        flightEntityRepository.delete(flightEntity);
    }

    @Override
    public Flight create(FlightIn flightIn) {
        FlightEntity flightEntity = FlightMapper.toEntity(flightIn);
        FlightEntity saved = flightEntityRepository.save(flightEntity);
        return FlightMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Flight> getList(Pageable pageable) {
        Iterable<FlightEntity> all = flightEntityRepository.findAll(pageable);
        return FlightMapper.toDTO(all);
    }
}
