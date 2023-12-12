package com.fdobrotv.touristagency.service.impl;

import com.fdobrotv.touristagency.dto.Transfer;
import com.fdobrotv.touristagency.dto.TransferIn;
import com.fdobrotv.touristagency.entity.CarEntity;
import com.fdobrotv.touristagency.entity.TransferEntity;
import com.fdobrotv.touristagency.mapper.TransferMapper;
import com.fdobrotv.touristagency.repository.CarEntityRepository;
import com.fdobrotv.touristagency.repository.TransferEntityRepository;
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
public class TransferServiceImpl implements CRUDService<Transfer, TransferIn> {

    @Autowired
    public TransferEntityRepository transferEntityRepository;

    @Autowired
    public CarEntityRepository carEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public Transfer getById(UUID id) {
        Optional<TransferEntity> transferByID = transferEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Transfer by id");
        return TransferMapper.toDTO(transferByID.orElseThrow(unableToFindResource));
    }

    @Override
    public void deleteById(UUID id) {
        Optional<TransferEntity> TransferEntityOptional = transferEntityRepository.findById(id);
        Supplier<ResponseStatusException> unableToFindResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to delete Transfer by id");
        TransferEntity TransferEntity = TransferEntityOptional.orElseThrow(unableToFindResource);
        transferEntityRepository.delete(TransferEntity);
    }

    @Override
    public Transfer create(TransferIn transferIn) {
        CarEntity carEntity = getCarEntity(transferIn);
        TransferEntity transferEntity = TransferMapper.toEntity(
                transferIn,
                carEntity
        );
        TransferEntity saved = transferEntityRepository.save(transferEntity);
        return TransferMapper.toDTO(saved);
    }

    private CarEntity getCarEntity(TransferIn transferIn) {
        Optional<CarEntity> carEntityOptional = carEntityRepository.findById(transferIn.getCarId());
        Supplier<ResponseStatusException> unableToFindCarModelResource =
                () -> new ResponseStatusException(NOT_FOUND, "Unable to find Car Model by id");
        return carEntityOptional.orElseThrow(unableToFindCarModelResource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transfer> getList(Pageable pageable) {
        Iterable<TransferEntity> all = transferEntityRepository.findAll(pageable);
        return TransferMapper.toDTO(all);
    }
}
