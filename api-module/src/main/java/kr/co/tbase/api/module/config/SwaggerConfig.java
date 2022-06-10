package kr.co.tbase.api.module.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.boot.starter.autoconfigure.SwaggerUiWebFluxConfiguration;
import springfox.boot.starter.autoconfigure.SwaggerUiWebMvcConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.configuration.OpenApiDocumentationConfiguration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.OAuth2Scheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@Configuration
@Profile("!real") // prifile에 대해서 나중에 확정되면 운영에서는 활성화하지 않음.
@Import({
        OpenApiDocumentationConfiguration.class,
        SpringDataRestConfiguration.class,
        BeanValidatorPluginsConfiguration.class,
        Swagger2DocumentationConfiguration.class,
        SwaggerUiWebFluxConfiguration.class,
        SwaggerUiWebMvcConfiguration.class
})
@AutoConfigureAfter({ WebMvcAutoConfiguration.class, JacksonAutoConfiguration.class,
                      HttpMessageConvertersAutoConfiguration.class, RepositoryRestMvcAutoConfiguration.class })
public class SwaggerConfig implements WebMvcConfigurer {
    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

//    @Value("${security.oauth2.client.client-secret}")
//    private String clientSecret;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false).select()
                .apis(RequestHandlerSelectors.basePackage("kr.co.tbase"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Pageable.class, SwaggerPageable.class)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContextOauth2()))
                .securitySchemes(Collections.singletonList(oauth2()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Skel Project Swagger")
                .description("Swagger")
                .version("1.0")
                .build();
    }

    public SecurityScheme oauth2() {
        return new OAuth2Scheme("oauth2", "password", "Connect with oauth", null,
                                accessTokenUri, null, Collections.emptyList(),
                                Collections.emptyList());
    }

    private SecurityContext securityContextOauth2() {
        return SecurityContext.builder().securityReferences(Arrays.asList(securityReferenceOauth2())).build();
    }

    private SecurityReference securityReferenceOauth2() {
        return new SecurityReference.SecurityReferenceBuilder().scopes(new AuthorizationScope[0])
                                                               .reference("oauth2").build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                                           .clientId(clientId)
//                .clientSecret(clientSecret)
                                           .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/apidoc")
                .setViewName("redirect:/swagger-ui/index.html");
    }

    @Getter
    private static class SwaggerPageable {
        @Parameter(name = "pageSize", example = "10")
        @Nullable
        private Integer size;
        @Parameter(name = "Results page you want to retrieve (0..N)", example = "0")
        @Nullable
        private Integer page;
        @Parameter(name = "Filed name,asc|desc", example = "filed,asc")
        @Nullable
        private String sort;
    }


}
