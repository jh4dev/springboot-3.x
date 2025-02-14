package com.jh4dev.myapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info =
    @Info(
            title = "This Is \"MyApp\" Project Swagger",
            description = "Nice one Sonny, Nice one Son, Nice one Sonny, Let's have another one!! ",
            version = "ver 1.0.0"
    )
)

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPI() {
        String[] paths = {"/users/**", "/admin/**"};

        return GroupedOpenApi.builder()
                .group("일반 사용자와 관리자용 User 도메인에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}
