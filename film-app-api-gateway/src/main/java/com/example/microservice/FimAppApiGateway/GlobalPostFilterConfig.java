package com.example.microservice.FimAppApiGateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalPostFilterConfig implements GlobalFilter {

    final Logger logger = LoggerFactory.getLogger(GlobalPostFilterConfig.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
       return chain.filter(exchange).then(Mono.fromRunnable(() -> {
           logger.info("Global Post Filter executes >>>>");
       }));
    }
}
