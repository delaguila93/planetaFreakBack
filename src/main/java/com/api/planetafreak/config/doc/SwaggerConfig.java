package com.api.planetafreak.config.doc;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private ApiInfo appInfo() {
		
		return new ApiInfo("Planeta Freak",
				"Proyecto creacion de proyectos Capgemini",
				"1.0",
				"Uso libre",
				new springfox.documentation.service.Contact("Planeta Freak", "http://www.fnac.com", "admin@planetafreak.com"),
				"Licencia de pago",
				"https://www.google.es",
				Collections.emptyList());
		
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.api.planetafreak"))
				.paths(PathSelectors.any())
				.build().apiInfo(appInfo());
		
	}

}
