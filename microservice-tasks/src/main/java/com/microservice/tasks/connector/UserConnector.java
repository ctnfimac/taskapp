package com.microservice.tasks.connector;

import com.microservice.tasks.connector.configuration.EndpointConfig;
import com.microservice.tasks.connector.configuration.HostConfig;
import com.microservice.tasks.connector.configuration.HttpConnectorConfig;
import com.microservice.tasks.connector.response.UserDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class UserConnector {
    private final String HOST = "api-users";
    private final String ENDPOINT = "get-user";
    private HttpConnectorConfig configuration;

    @Autowired
    public UserConnector(HttpConnectorConfig configuration) {
        this.configuration = configuration;
    }

    @CircuitBreaker(name = "api-users", fallbackMethod = "fallbackGetUser")
    public UserDTO getUser(Long userId) {
        System.out.println("Calling to api de users");
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
                .uri(urlEncoder -> urlEncoder.build(userId))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                response -> response.bodyToMono(String.class)
                        .flatMap(body -> Mono.error(new RuntimeException("Error al llamar a user-service: " + body)))
        )
                .bodyToMono(UserDTO.class)
                .share()
                .block();

    }

    public UserDTO fallbackGetUser(Long userId, CallNotPermittedException ex) {
        System.out.println("calling to fallbackGetUser-1");
        System.err.println("Error en fallback: " + ex.getMessage());
        ex.printStackTrace();
        return new UserDTO();
    }

    public UserDTO fallbackGetUser(Long userId, Exception ex) {
        System.out.println("calling to fallbackGetUser-2");

        /*throw new RuntimeException();*/
        System.err.println("Error en fallback: " + ex.getMessage());
        ex.printStackTrace();
        return new UserDTO();
    }
}
