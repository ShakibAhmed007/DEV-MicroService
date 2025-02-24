package com.example.microservice.FimAppApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalPreFilterConfig implements GlobalFilter {

    final Logger logger = LoggerFactory.getLogger(GlobalPreFilterConfig.class);


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Global Filter executes >>>>>");
        logger.info(exchange.getRequest().getPath().toString());
        logger.info(exchange.getRequest().getHeaders().toString());
        return chain.filter(exchange);
    }
}
