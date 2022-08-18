package com.example.demo.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Api(value="RHO Exchange Rate Challenge - REST API")
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.exchangerate")).build().apiInfo(metaInfo());
    }


    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "RHO Exchange Rate Challenge",
                "RESTfull API to get exchange rates and use them for conversion calculations",
                "1.0",
                "Terms of Service",
                "João Pedro Martins",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"
        );
        return apiInfo;
    }
}
