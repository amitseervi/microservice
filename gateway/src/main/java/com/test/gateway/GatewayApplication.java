package com.test.gateway;

import com.test.gateway.filter.TestGatewayFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           TestGatewayFilterFactory testGatewayFilterFactory) {
        return builder.routes()
                .route("first_service", r -> r.path("/api/first/**")
						.filters(f->
								f.filter(testGatewayFilterFactory.apply(new TestGatewayFilterFactory.Config("underworld","http://localhost:8001/api/second/error"))))
                        .uri("http://localhost:8002"))
                .route("second_service", r -> r.path("/api/second/**")
                        .uri("http://localhost:8003"))
                .build();
    }

}
