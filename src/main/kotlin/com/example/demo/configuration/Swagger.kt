package com.example.demo.configuration


import com.example.demo.SpringKotlinBackendApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(SpringKotlinBackendApplication::class.java.`package`.name))
        .paths(PathSelectors.any())
        .build()
}

//open swagger:
//http://localhost:8080/swagger-ui.html

//para ver desde emulador de androidstudio:
//http://10.0.2.2:8080/swagger-ui.html

