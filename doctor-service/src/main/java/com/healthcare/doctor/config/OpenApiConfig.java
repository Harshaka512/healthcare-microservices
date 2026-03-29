package com.healthcare.doctor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI doctorServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Doctor Service API")
                        .description("REST API for doctor profiles and availability")
                        .version("1.0"));
    }
}
