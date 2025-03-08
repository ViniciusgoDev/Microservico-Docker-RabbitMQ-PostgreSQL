package com.microsservico.orderService.Controller;

import com.microsservico.orderService.model.Dto.OrderRequestDTO;
import com.microsservico.orderService.model.Dto.OrderResponseDTO;
import com.microsservico.orderService.model.enums.ShippingStatus;
import com.microsservico.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderControler {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody OrderRequestDTO request) {
        orderService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public OrderResponseDTO getDetails(@RequestParam("id") UUID id){
       return orderService.search(id);
    }

    @PutMapping
    public ResponseEntity<Void> updateStatus(@RequestParam("id") UUID id,  @RequestParam("Status") ShippingStatus status){
        orderService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }


}
