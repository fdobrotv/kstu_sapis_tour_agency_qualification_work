package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.FoodOptionsApi;
import com.fdobrotv.touristagency.dto.FoodOption;
import com.fdobrotv.touristagency.dto.FoodOptionIn;
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
public class FoodOptionController implements FoodOptionsApi {

    private CRUDService<FoodOption, FoodOptionIn> foodOptionService;

    public FoodOptionController(CRUDService<FoodOption, FoodOptionIn> foodOptionService) {
        this.foodOptionService = foodOptionService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return FoodOptionsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteFoodOptionById(UUID id) {
        foodOptionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<FoodOption> createFoodOption(FoodOptionIn foodOptionIn) {
        FoodOption foodOption = foodOptionService.create(foodOptionIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodOption);
    }

    @Override
    public ResponseEntity<List<FoodOption>> listFoodOptions(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<FoodOption> foodOptions = foodOptionService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(foodOptions);
    }
}
