package com.test.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class TestHeaderFilter implements GatewayFilter {
    private final TestGatewayFilterFactory.Config config;

    public TestHeaderFilter(TestGatewayFilterFactory.Config config) {
        this.config = config;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if (request.getHeaders().containsKey(config.getHeaderName())) {
            return chain.filter(exchange);
        } else {
            ServerWebExchangeUtils.setResponseStatus(exchange, HttpStatus.SEE_OTHER);
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().set("Location", config.getErrorRedirectPath());
            return response.setComplete();
        }
    }
}
