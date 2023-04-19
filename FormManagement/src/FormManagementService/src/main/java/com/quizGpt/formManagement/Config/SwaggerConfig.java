package com.quizGpt.formManagement.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /*
    User API
     */
    @Bean
    public GroupedOpenApi userApi() {
        final String[] packagesToScan = {"com.quizGpt.formManagement"};
        return GroupedOpenApi
                .builder()
                .group("APIs")
                .packagesToScan(packagesToScan)
                .pathsToMatch("")
                //.addOpenApiCustomiser(statusApiCostumizer())
                .build();
    }

    private OpenApiCustomiser statusApiCostumizer() {
        return openAPI -> openAPI
                .info(new Info()
                        .title("Springboot & OpenAPI")
                        .description("This is the endpoint documentation for the form service using OpenAPI")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Witnick Joseph")
                                .url("https://github.com/witnick/QuizGPT/tree/formManagementService")
                                .email("witnick2joseph@gmail.com")));
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Contact Application API").description(
                        "This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
    }

}