package kr.co.tbase.api.module.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import kr.co.tbase.oauth2.resource.config.EnableTbaseResourceServer;

/**
 * EnableJpaAuditing : JPA Audit 활성화 (생성자/수정자/생성일자/수정일자)
 * EnableTbaseResourceServer : TBase OAuth 인증 활성화용 어노테이션
 */
@Configuration
@EnableJpaAuditing
@EnableTbaseResourceServer
public class ApplicationConfig {
}
