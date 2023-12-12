package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.FlightsApi;
import com.fdobrotv.touristagency.dto.Flight;
import com.fdobrotv.touristagency.dto.FlightIn;
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
public class FlightController implements FlightsApi {

    private CRUDService<Flight, FlightIn> flightService;

    public FlightController(CRUDService<Flight, FlightIn> flightService) {
        this.flightService = flightService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return FlightsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteFlightById(UUID id) {
        flightService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Flight> createFlight(FlightIn flightIn) {
        Flight flight = flightService.create(flightIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(flight);
    }

    @Override
    public ResponseEntity<List<Flight>> listFlights(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<Flight> flights = flightService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }
}
