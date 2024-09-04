package com.r3s.gateway.config;

import com.r3s.gateway.service.impl.ApiRouteLocatorImpl;
import com.r3s.gateway.service.RouteService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteService routeService, RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiRouteLocatorImpl(routeLocatorBuilder, routeService);
    }
}
