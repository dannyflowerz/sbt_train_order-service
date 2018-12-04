package io.blueharvest.training.ms.orderservice.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.blueharvest.training.ms.orderservice.Constants;
import io.blueharvest.training.ms.orderservice.order.domain.exception.OrderException;
import io.blueharvest.training.ms.orderservice.order.domain.model.ShippingRequest;

@Service
class OrderServiceImpl implements OrderService {

    private final InventoryServiceClient inventoryServiceClient;
    private final ShippingServiceClient shippingServiceClient;

    @Autowired
    public OrderServiceImpl(InventoryServiceClient inventoryServiceClient, ShippingServiceClient shippingServiceClient) {
        this.inventoryServiceClient = inventoryServiceClient;
        this.shippingServiceClient = shippingServiceClient;
    }

    @Override
    public void placeOrder(String productName) {
        String reservationCode = inventoryServiceClient.reserveProduct(productName).getReservationCode();
        if (Constants.NO_PRODUCT_AVAILABLE_CODE.equals(reservationCode)) {
            throw new OrderException("Unable to place new order, out of stock");
        }
        shippingServiceClient.requestShipping(new ShippingRequest(reservationCode));
    }

}
