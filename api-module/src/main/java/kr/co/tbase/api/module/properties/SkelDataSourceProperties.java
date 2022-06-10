package kr.co.tbase.api.module.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "skel.datasource")
public class SkelDataSourceProperties {
    DataSourceProperties master;
}
