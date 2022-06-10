package kr.co.tbase.api.module.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MybatisConfig {
	@Value("classpath*:mappers/**/*.xml")
	private Resource[] mapperLocations;

	@Value("file:config/mybatis-config.xml") // 실행 파일과 동일한 경로의 config 폴더의 설정 값을 읽어옴
	private Resource configLocation;

	@Bean
	public SqlSessionTemplate mybatisSession(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(mapperLocations);
		sessionFactory.setConfigLocation(configLocation);
		return new SqlSessionTemplate(sessionFactory.getObject());
	}
}
