package io.blueharvest.training.ms.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableHystrixDashboard // http://localhost:8280/hystrix --> http://localhost:8280/actuator/hystrix.stream
@EnableFeignClients
@EnableEurekaClient  // also works without this annotation just because the dependency and configuration are there
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}

