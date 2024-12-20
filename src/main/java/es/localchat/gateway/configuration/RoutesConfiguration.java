package es.localchat.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

    private final static String[] HTTP_METHODS = new String[]{"GET", "POST", "PUT", "DELETE", "PATCH"};
    private final static String[] GENERAL_SERVICE_PATHS = new String[] {
            "/api/v1/user/**", "/api/v1/profilelike/**", "/api/v1/chat/private/**", "/api/v1/file/**",
            "/api/v1/event/custom/**", "/api/v1/avatar/**", "/api/v1/auth/**"
    };
    private final static String[] LOCALCHAT_SERVICE_PATHS = new String[] {"/api/v1/chat/local/**"};

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path(LOCALCHAT_SERVICE_PATHS)
                        .and().method(HTTP_METHODS)
                        .uri("lb://localchat-service")
                )
                .route(p -> p
                        .path(GENERAL_SERVICE_PATHS)
                        .and().method(HTTP_METHODS)
                        .uri("lb://localchat-service")
                )
                .build();
    }
}