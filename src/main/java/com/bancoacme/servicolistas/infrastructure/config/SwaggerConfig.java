package com.bancoacme.servicolistas.infrastructure.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI documentacaoServicoListas() {
        return new OpenAPI()
                .info(new Info()
                        .title("Serviço de Listas Dinâmicas")
                        .description("API para gerenciamento de listas com campos dinâmicos e filtros personalizados.")
                        .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi listasGroup() {
        return GroupedOpenApi.builder()
                .group("Listas")
                .pathsToMatch("/listas/**")
                .build();
    }
}
