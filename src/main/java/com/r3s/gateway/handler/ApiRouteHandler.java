package com.r3s.gateway.handler;


import com.r3s.gateway.config.GatewayRouteRefresher;
import com.r3s.gateway.model.db.ApiRoute;
import com.r3s.gateway.model.request.ApiRouteRq;
import com.r3s.gateway.model.response.ApiRouteRs;
import com.r3s.gateway.service.RouteService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.List;


@RestController
@RequestMapping("/gateway")
public class ApiRouteHandler {
    private final RouteService routeService;

    private final RouteLocator routeLocator;

    private final GatewayRouteRefresher gatewayRoutesRefresher;

    public ApiRouteHandler(RouteService routeService, RouteLocator routeLocator, GatewayRouteRefresher gatewayRoutesRefresher) {
        this.routeService = routeService;
        this.routeLocator = routeLocator;
        this.gatewayRoutesRefresher = gatewayRoutesRefresher;
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody ApiRouteRq apiRouteRq) {
        Mono<Void> response = routeService.create(apiRouteRq)
                .subscribeOn(Schedulers.boundedElastic());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        Mono<List<ApiRouteRs>> result = routeService.getAll()
                .map(this::convertApiRouteIntoApiRouteResponse)
                .collectList()
                .subscribeOn(Schedulers.boundedElastic());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        Mono<ApiRouteRs> result = routeService.getById(id)
                .map(this::convertApiRouteIntoApiRouteResponse).subscribeOn(Schedulers.boundedElastic());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshRoutes() {
        gatewayRoutesRefresher.refreshRoutes();
        return new ResponseEntity<>("Routes reloaded successfully", HttpStatus.OK);
    }

    private ApiRouteRs convertApiRouteIntoApiRouteResponse(ApiRoute apiRoute) {
        return ApiRouteRs.builder()
                .id(apiRoute.getId())
                .routeIdentifier(apiRoute.getRouteIdentifier())
                .uri(apiRoute.getUri())
                .path(apiRoute.getPath())
                .method(apiRoute.getMethod())
                .createdDate(apiRoute.getCreatedDate())
                .updatedDate(apiRoute.getUpdatedDate())
                .deleteddDate(apiRoute.getDeleteddDate())
                .build();
    }
}
