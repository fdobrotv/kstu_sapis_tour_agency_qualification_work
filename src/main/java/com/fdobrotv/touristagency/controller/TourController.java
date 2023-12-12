package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.ToursApi;
import com.fdobrotv.touristagency.dto.Tour;
import com.fdobrotv.touristagency.dto.TourIn;
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
public class TourController implements ToursApi {

    private CRUDService<Tour, TourIn> tourService;

    public TourController(CRUDService<Tour, TourIn> tourService) {
        this.tourService = tourService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return ToursApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteTourById(UUID id) {
        tourService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Tour> createTour(TourIn tourIn) {
        Tour tour = tourService.create(tourIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(tour);
    }

    @Override
    public ResponseEntity<List<Tour>> listTours(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<Tour> tours = tourService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(tours);
    }
}
