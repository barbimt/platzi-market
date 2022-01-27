package com.platzi.market.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//le indicamos q ene sta clase vamos a habilitar Swagger2
@EnableSwagger2
public class SwaggerConfig {
    //vamos a incluir un Bean, es un método público que retornar un Doccket de SpringFox.documentation.spring, es especifico para la documentación de Spring
    //en el new docket le decimos que tipo de documentacion vamos a usar
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                //le decimos que queremos q exporte o exponga en la documentacion
                .select()
                //solamente los q esten en el controller, van a ser expuestos a través de la documentación, porque ahi tenemoms nuestros endpoints del API
                //en basePackage le vamos a decir el nombre del paquete donde estan nuestros controladores
                .apis(RequestHandlerSelectors.basePackage("com.platzi.market.web.controller"))
                //tenemos que construir nuestra respuesta:
                .build().apiInfo(apiEndPointInfo());

    }

    private ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("API of products")
                .description("Services for consultation of products")
                .build();
    }
}
