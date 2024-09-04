package com.r3s.gateway.model.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "apiroute")
public class ApiRoute {
    @Id
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deleteddDate;
    private String routeIdentifier;
    private String uri;
    private String method;
    private String path;
}
