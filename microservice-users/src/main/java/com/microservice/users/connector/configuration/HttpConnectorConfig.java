package com.microservice.users.connector.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "http-connector")
@Setter
@Getter
public class HttpConnectorConfig {
    private HashMap<String, HostConfig> hosts;
}
