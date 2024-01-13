package com.matawan.nicefc.config;


import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class postGreSQLConfig {
    @Bean
    @Primary
    public DataSource embeddedPostGreSqlDb() throws Exception {
        DataSource embeddedPostgresDS = EmbeddedPostgres.builder()
                .start().getPostgresDatabase();

        return embeddedPostgresDS;
    }
}
