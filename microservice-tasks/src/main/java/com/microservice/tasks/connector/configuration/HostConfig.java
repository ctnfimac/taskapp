package com.microservice.tasks.connector.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class HostConfig {
    private String host;
    private int port;
    private HashMap<String, EndpointConfig> endpoints;
}
