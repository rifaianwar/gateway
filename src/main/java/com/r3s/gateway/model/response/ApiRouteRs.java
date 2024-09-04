package com.r3s.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiRouteRs {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deleteddDate;
    private String routeIdentifier;
    private String uri;
    private String method;
    private String path;
}
