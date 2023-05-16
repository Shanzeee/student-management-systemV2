package com.brvsk.studentmanagementsystemV2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class SwaggerConfig {

    @Bean
    public OpenAPI apiDescription(){
        return new OpenAPI()
                .servers(List.of(getDefaultServer()))
                .info(new Info().title("Core-API")
                        .description("Main component")
                        .version("1.0"));
    }

    private Server getDefaultServer() {
        return new Server().url("/").description("Default server URL");
    }
}
