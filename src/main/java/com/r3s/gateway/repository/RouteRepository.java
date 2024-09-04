package com.r3s.gateway.repository;


import com.r3s.gateway.model.db.ApiRoute;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface RouteRepository extends ReactiveCrudRepository<ApiRoute, Long> {
}
