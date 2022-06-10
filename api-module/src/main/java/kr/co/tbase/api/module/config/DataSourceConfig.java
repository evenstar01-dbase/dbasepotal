package kr.co.tbase.api.module.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

import kr.co.tbase.api.module.properties.SkelDataSourceProperties;

@Configuration
public class DataSourceConfig {
    @Autowired(required = false)
    SkelDataSourceProperties skelDataSourceProperties;

    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        if (skelDataSourceProperties.getMaster() != null) {
            return skelDataSourceProperties.getMaster().initializeDataSourceBuilder().type(HikariDataSource.class)
                                          .build();
        }
        return null;
    }
}
