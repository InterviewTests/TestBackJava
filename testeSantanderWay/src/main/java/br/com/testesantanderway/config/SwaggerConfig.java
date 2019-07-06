package br.com.testesantanderway.config;

import br.com.testesantanderway.modelo.Sistema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.testesantanderway.controller"))
                .build()
                .apiInfo(apiInfo())
                .ignoredParameterTypes(Sistema.class).globalOperationParameters(
                        Arrays.asList(new ParameterBuilder()
                                .name("Authorization")
                                .description("Header for Token JWT")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false).build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Gestão de Gastos Santander Way")
                .description("\"Gestão de Gastos\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .build();
    }
}