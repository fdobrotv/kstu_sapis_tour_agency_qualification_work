package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.CarModelsApi;
import com.fdobrotv.touristagency.dto.CarModel;
import com.fdobrotv.touristagency.dto.CarModelIn;
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
public class CarModelController implements CarModelsApi {

    private CRUDService<CarModel, CarModelIn> carModelService;

    public CarModelController(CRUDService<CarModel, CarModelIn> carModelService) {
        this.carModelService = carModelService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CarModelsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteCarModelById(UUID id) {
        carModelService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<CarModel> showCarModelById(UUID id) {
        CarModel carModel = carModelService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(carModel);
    }

    @Override
    public ResponseEntity<CarModel> createCarModel(CarModelIn carModelIn) {
        CarModel carModel = carModelService.create(carModelIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(carModel);
    }

    @Override
    public ResponseEntity<List<CarModel>> listCarModels(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<CarModel> carModels = carModelService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(carModels);
    }
}
