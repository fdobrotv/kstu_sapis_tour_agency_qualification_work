package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.HotelsApi;
import com.fdobrotv.touristagency.dto.Hotel;
import com.fdobrotv.touristagency.dto.HotelIn;
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
public class HotelController implements HotelsApi {

    private CRUDService<Hotel, HotelIn> hotelService;

    public HotelController(CRUDService<Hotel, HotelIn> hotelService) {
        this.hotelService = hotelService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return HotelsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteHotelById(UUID id) {
        hotelService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Hotel> createHotel(HotelIn hotelIn) {
        Hotel hotel = hotelService.create(hotelIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @Override
    public ResponseEntity<List<Hotel>> listHotels(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<Hotel> hotels = hotelService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }
}
