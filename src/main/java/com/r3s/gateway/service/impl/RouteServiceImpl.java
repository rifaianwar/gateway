package com.r3s.gateway.service.impl;

import com.r3s.gateway.config.GatewayRouteRefresher;
import com.r3s.gateway.model.db.ApiRoute;
import com.r3s.gateway.model.request.ApiRouteRq;
import com.r3s.gateway.repository.RouteRepository;
import com.r3s.gateway.service.RouteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final GatewayRouteRefresher gatewayRouteRefresher;

    public RouteServiceImpl(RouteRepository routeRepository, GatewayRouteRefresher gatewayRouteRefresher) {
        this.routeRepository = routeRepository;
        this.gatewayRouteRefresher = gatewayRouteRefresher;
    }

    @Override
    public Flux<ApiRoute> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Mono<Void> create(ApiRouteRq apiRouteRq) {
        LocalDateTime currentDate = LocalDateTime.now();
        ApiRoute apiRoute = new ApiRoute();
        apiRoute.setRouteIdentifier(apiRouteRq.getRouteIdentifier());
        apiRoute.setUri(apiRouteRq.getUri());
        apiRoute.setMethod(apiRouteRq.getMethod());
        apiRoute.setPath(apiRouteRq.getPath());
        apiRoute.setCreatedDate(currentDate);
        Mono<ApiRoute> route = routeRepository.save(apiRoute);
        return route.doOnSuccess(obj -> gatewayRouteRefresher.refreshRoutes()).then();
    }

    @Override
    public Mono<ApiRoute> getById(Long id) {
        return routeRepository.findById(id);
    }

}
