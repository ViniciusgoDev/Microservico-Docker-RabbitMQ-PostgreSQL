package com.microsservico.orderService.mapper;

import com.microsservico.orderService.model.Dto.OrderResponseDTO;
import com.microsservico.orderService.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;




@RequiredArgsConstructor
@Component
public class OrderMapper {

    public OrderResponseDTO toOrderResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getCustomerId(),
                order.getStoreId(),
                order.getTotalPrice(),
                order.getDeliveryAddress(),
                order.getPickupAddress(),
                order.getShippingStatus(),
                order.getCreatedAt()
//                order.getProducts()

        );
    }



}
