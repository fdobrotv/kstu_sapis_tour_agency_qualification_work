package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.CarMarksApi;
import com.fdobrotv.touristagency.dto.CarMark;
import com.fdobrotv.touristagency.dto.CarMarkIn;
import com.fdobrotv.touristagency.service.CRUDService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("${openapi.touristAgency.base-path:/v1}")
public class CarMarkController implements CarMarksApi {

    private CRUDService<CarMark, CarMarkIn> carMarkService;

    public CarMarkController(CRUDService<CarMark, CarMarkIn> carMarkService) {
        this.carMarkService = carMarkService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CarMarksApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteCarMarkById(UUID id) {
        carMarkService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<CarMark> showCarMarkById(UUID id) {
        CarMark carMark = carMarkService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carMark);
    }

    @Override
    public ResponseEntity<CarMark> createCarMark(CarMarkIn carModelIn) {
        CarMark carMark = carMarkService.create(carModelIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(carMark);
    }

    @Override
    public ResponseEntity<List<CarMark>> listCarMarks(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<CarMark> carMarks = carMarkService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(carMarks);
    }
}
