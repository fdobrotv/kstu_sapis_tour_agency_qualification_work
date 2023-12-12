package com.fdobrotv.touristagency.mapper;

import com.fdobrotv.touristagency.dto.Transfer;
import com.fdobrotv.touristagency.dto.TransferIn;
import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.dto.Transfer;
import com.fdobrotv.touristagency.entity.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TransferMapper {
    public static Transfer toDTO(TransferEntity transferEntity) {
        Transfer transfer = new Transfer();
        transfer.id(transferEntity.getId());
        transfer.name(transferEntity.getName());
        transfer.car(CarMapper.toDTO(transferEntity.getCar()));
        transfer.departureCoordinates(transferEntity.getDepartureCoordinates());
        transfer.arrivalCoordinates(transferEntity.getArrivalCoordinates());
        transfer.departureDateTime(transferEntity.getDepartureDateTime().atOffset(ZoneOffset.UTC));
        transfer.arrivalDateTime(transferEntity.getArrivalDateTime().atOffset(ZoneOffset.UTC));
        transfer.setIsGuideIncluded(transferEntity.getIsGuideIncluded());
        transfer.setPrice(BigDecimal.valueOf(transferEntity.getPrice().longValue()));
        return transfer;
    }

    public static List<Transfer> toDTO(List<TransferEntity> transferEntities) {
        return transferEntities.stream()
                .map(TransferMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static List<Transfer> toDTO(Iterable<TransferEntity> transferEntities) {
        Stream<TransferEntity> targetStream = StreamSupport.stream(transferEntities.spliterator(), false);
        return toDTO(targetStream.toList());
    }

    public static TransferEntity toEntity(TransferIn transferIn, CarEntity carEntity) {
        TransferEntity transferEntity = new TransferEntity();
        transferEntity.setPrice(transferIn.getPrice().toBigInteger());
        transferEntity.setName(transferIn.getName());
        transferEntity.setCar(carEntity);
        transferEntity.setDepartureCoordinates(transferIn.getDepartureCoordinates());
        transferEntity.setArrivalCoordinates(transferIn.getArrivalCoordinates());
        transferEntity.setDepartureDateTime(transferIn.getDepartureDateTime().toInstant());
        transferEntity.setArrivalDateTime(transferIn.getArrivalDateTime().toInstant());
        transferEntity.setIsGuideIncluded(transferIn.getIsGuideIncluded());
        return transferEntity;
    }
}
