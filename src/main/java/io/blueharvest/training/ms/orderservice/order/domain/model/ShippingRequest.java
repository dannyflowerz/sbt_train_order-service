package io.blueharvest.training.ms.orderservice.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ShippingRequest {

    private String reservationCode;

}
