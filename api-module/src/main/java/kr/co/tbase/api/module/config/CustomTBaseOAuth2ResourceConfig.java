package kr.co.tbase.api.module.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import kr.co.tbase.oauth2.resource.config.TBaseOAuth2ResourceConfig;

/**
 * OAuth 인증 관련 설정 추가
 */
@Configuration
@Order(1)
@EnableResourceServer
public class CustomTBaseOAuth2ResourceConfig extends TBaseOAuth2ResourceConfig {
    public CustomTBaseOAuth2ResourceConfig(ResourceServerProperties sso) {
        super(sso);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        final String[] freePassList = {
                "/guide/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/swagger-ui",
                "/swagger-ui**",
                "/swagger-ui/**",
                "/swagger-ui.html#**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/v2/api-docs/**",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/api/**" // 임시
        };

        // /guide/** 하위에 있는 항목은 OAuth 비로그인 사용자도 접근할 수 있게 permitAll
        http.authorizeRequests().antMatchers(freePassList).permitAll();
    }
}