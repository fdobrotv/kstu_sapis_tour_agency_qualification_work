package com.fdobrotv.touristagency.controller;

import com.fdobrotv.touristagency.api.TransfersApi;
import com.fdobrotv.touristagency.dto.Transfer;
import com.fdobrotv.touristagency.dto.TransferIn;
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
public class TransferController implements TransfersApi {

    private CRUDService<Transfer, TransferIn> transferService;

    public TransferController(CRUDService<Transfer, TransferIn> transferService) {
        this.transferService = transferService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return TransfersApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> deleteTransferById(UUID id) {
        transferService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Transfer> createTransfer(TransferIn transferIn) {
        Transfer transfer = transferService.create(transferIn);
        return ResponseEntity.status(HttpStatus.CREATED).body(transfer);
    }

    @Override
    public ResponseEntity<List<Transfer>> listTransfers(Integer limit) {
        //TODO: De hard-code it.
        int pageNumber = 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, limit);
        List<Transfer> transfers = transferService.getList(pageRequest);
        return ResponseEntity.status(HttpStatus.OK).body(transfers);
    }
}
