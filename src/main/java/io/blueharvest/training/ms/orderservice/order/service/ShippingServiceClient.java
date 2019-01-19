package io.blueharvest.training.ms.orderservice.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.blueharvest.training.ms.orderservice.order.domain.model.ShippingRequest;
import lombok.extern.slf4j.Slf4j;

@FeignClient(name = "shipping-service", fallback = ShippingServiceClient.ShippingRequestFallback.class)
public interface ShippingServiceClient {

    @Slf4j
    @Component
    class ShippingRequestFallback implements ShippingServiceClient {

        @Override
        public void requestShipping(ShippingRequest shippingRequest) {
            log.warn("Unable to request shipping of product with reservation code " + shippingRequest.getReservationCode() +
                    " at the moment. Request offloaded to a retry queue.");
        }

    }

    @PostMapping("/requests")
    void requestShipping(@RequestBody ShippingRequest shippingRequest);

}
