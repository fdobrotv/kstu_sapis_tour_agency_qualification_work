package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.RoomsApi;
import com.fdobrotv.touristagency.dto.Room;
import com.fdobrotv.touristagency.dto.RoomIn;
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
public class RoomController implements RoomsApi {

    private CRUDService<Room, RoomIn> roomService;

    public RoomController(CRUDService<Room, RoomIn> roomService) {
        this.roomService = roomService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return RoomsApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteRoomById(UUID id) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Room> createRoom(RoomIn roomIn) {
        Room room = roomService.create(roomIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);
    }

    @Override
    public ResponseEntity<List<Room>> listRooms(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<Room> rooms = roomService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }
}
