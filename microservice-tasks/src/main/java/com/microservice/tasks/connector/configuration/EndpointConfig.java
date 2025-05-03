package com.microservice.tasks.connector.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndpointConfig {
    private String url;
    private int readTimeout;
    private int writeTimeout;
    private int connectionTimeout;
}
