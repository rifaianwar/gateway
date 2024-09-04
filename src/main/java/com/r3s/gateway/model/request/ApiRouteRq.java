package com.r3s.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRouteRq {
    private String routeIdentifier;
    private String uri;
    private String method;
    private String path;
}
