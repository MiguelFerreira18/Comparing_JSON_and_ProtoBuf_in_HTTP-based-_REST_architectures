package isep.ipp.pt.Gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class GatewayConfig {
    private final String[] DOCS_PATHS = {
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**",
            "/api-docs",
            "/favicon.icon",
            "/swagger-config.json"
    };
    private final String[] PATHS = {
            "/**"
    };
    private final String localhost = "localhost";

    @Value("${kubernetes.protobuf.api.url}")
    private String kubernetesProtobufUrl;
    @Value("${kubernetes.json.api.url}")
    private String kubernetesJsonUrl;
    @Value("${is.in.kubernetes}")
    private boolean isInKubernetes;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        System.out.println("--------> Is in kubernetes: " + isInKubernetes+ " <---------");
        String jsonURL = "http://" + (isInKubernetes ? kubernetesJsonUrl : localhost) + ":8080";
        String protobufURL = "http://" + (isInKubernetes ? kubernetesProtobufUrl : localhost) + ":8081";

        return builder.routes()
                .route("json-docs", r -> r
                        .path("/json/v3/api-docs/**", "/json/swagger-ui/**", "/json/swagger-resources/**")
                        .filters(f -> f.rewritePath("/json/(?<segment>.*)", "/${segment}"))
                        .uri(jsonURL))

                .route("protobuf-docs", r -> r
                        .path("/protobuf/v3/api-docs/**", "/protobuf/swagger-ui/**", "/protobuf/swagger-resources/**")
                        .filters(f -> f.rewritePath("/protobuf/(?<segment>.*)", "/${segment}"))
                        .uri(protobufURL))
                .route("json-route", r -> r
                        .path(PATHS)
                        .and()
                        .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .uri(jsonURL)
                )
                .route("protobuf-route", r -> r
                        .path(PATHS)
                        .and()
                        .header("Content-Type", MediaType.APPLICATION_PROTOBUF_VALUE)
                        .uri(protobufURL)
                )
                .build();
    }
}
