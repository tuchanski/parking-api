package com.tuchanski.parking_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Parking API")
                .version("1.0")
                .description("API created to help managing a parking system")
                .contact(new Contact()
                        .name("Tuchanski")
                        .email("guilherme.tuchanski@gmail.com")));
    }

}
