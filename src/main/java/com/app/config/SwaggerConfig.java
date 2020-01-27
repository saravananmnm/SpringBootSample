package com.app.config;

import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{
	@Bean
	public Docket api() {
		 AuthorizationScope[] authScopes = new AuthorizationScope[1];
	     authScopes[0] = new AuthorizationScopeBuilder().scope("global").description("full access").build();
	     SecurityReference securityReference = SecurityReference.builder().reference("Authorization-Key")
	             .scopes(authScopes).build();

	     List<SecurityContext> securityContexts = Arrays.asList(
	             SecurityContext.builder().securityReferences(Arrays.asList(securityReference)).build());
		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(Arrays.asList(new ApiKey("Saravanan", "Authorization", "header")))
	            .securityContexts(securityContexts)
				.select()
				.apis(basePackage("com.app.controller"))
				.paths(regex("/.*"))
				.build()
				.apiInfo(apiInfo());
	}
	
	@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	


	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring Boot REST API")
				.description("REST API")
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
}
