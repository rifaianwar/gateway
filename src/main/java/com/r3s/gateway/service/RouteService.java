package com.r3s.gateway.service;

import com.r3s.gateway.model.db.ApiRoute;
import com.r3s.gateway.model.request.ApiRouteRq;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RouteService {
    Flux<ApiRoute> getAll();
    Mono<Void> create(ApiRouteRq apiRouteRq);
    Mono<ApiRoute> getById(Long id);

}
