package io.blueharvest.training.ms.orderservice.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.blueharvest.training.ms.orderservice.order.domain.exception.OrderException;
import io.blueharvest.training.ms.orderservice.order.domain.model.ReservationResponse;

@FeignClient(name = "inventory-service", fallback = InventoryServiceClient.ProductReservationFallback.class)
public interface InventoryServiceClient {

    @Component
    class ProductReservationFallback implements InventoryServiceClient {

        @Override
        public ReservationResponse reserveProduct(String productName) {
            // needs exception handler to extract this message into the response
            throw new OrderException("Reservation is currently not possible. Please try again later.");
        }

    }

    @PostMapping("/products/{product-name}/reservations")
    ReservationResponse reserveProduct(@PathVariable("product-name") String productName);

}
