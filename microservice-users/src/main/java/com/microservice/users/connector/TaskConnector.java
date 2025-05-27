package com.microservice.users.connector;

import com.microservice.users.connector.configuration.EndpointConfig;
import com.microservice.users.connector.configuration.HostConfig;
import com.microservice.users.connector.configuration.HttpConnectorConfig;
import com.microservice.users.connector.response.TaskBlockDTO;
import com.microservice.users.connector.response.TaskDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class TaskConnector {
    private final String HOST = "api-tasks";
    private final String ENDPOINT = "get-task-byuserandblockfinished";
    private HttpConnectorConfig configuration;

    @Autowired
    public TaskConnector(HttpConnectorConfig configuration) {
        this.configuration = configuration;
    }

    @CircuitBreaker(name = "api-tasks", fallbackMethod = "fallbackGetTask")
    public List<TaskDTO> getTasksByUserAndBlockFinished(Long blockId, Long userId) {
        System.out.println("Calling to api de tasks");
        HostConfig hostConfig = configuration.getHosts().get(HOST);
        EndpointConfig endpointConfig = hostConfig.getEndpoints().get(ENDPOINT);

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpointConfig.getConnectionTimeout()))
                .doOnConnected(conn -> conn
                        .addHandler(new ReadTimeoutHandler(endpointConfig.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandler(new WriteTimeoutHandler(endpointConfig.getWriteTimeout(), TimeUnit.MILLISECONDS)));


        WebClient client = WebClient.builder()
                .baseUrl("http://" + hostConfig.getHost() + ":" + hostConfig.getPort() + endpointConfig.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();


        return client.get()
                .uri(urlEncoder -> urlEncoder.build(blockId,userId))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaskDTO>>() {})
                .share()
                .block();

    }

    public List<TaskDTO> fallbackGetTask(Long blockId, Long userId, CallNotPermittedException ex) {
        System.out.println("calling to fallbackGetTask");

        return List.of();
    }

    public List<TaskDTO> fallbackGetTask( Long blockId, Long userId, Exception ex) {
        System.out.println("calling to fallbackGetTask");

        throw new RuntimeException();
    }
}

