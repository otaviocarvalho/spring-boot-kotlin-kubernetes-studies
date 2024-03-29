package com.otavio.transactions.infrastructure.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class ControllerConfiguration {

    @Bean
    fun apiDocumentation(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.otavio.transactions.infrastructure.entrypoint.rest.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(ApiInfoBuilder()
                    .title("Transactions")
                    .description("Transactions microservice")
                    .build())
            .useDefaultResponseMessages(false)
}
