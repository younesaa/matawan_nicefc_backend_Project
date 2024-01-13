package com.matawan.nicefc.config;


import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
/**
 * Configuration class for providing an embedded PostGreSQL DataSource.
 */
@Configuration
public class postGreSQLConfig {
    private static final Logger logger = LoggerFactory.getLogger(postGreSQLConfig.class);

    /**
     * Configures and provides an embedded PostGreSQL DataSource.
     *
     * @return The configured embedded PostGreSQL DataSource.
     * @throws Exception If an error occurs while starting the embedded PostGreSQL instance.
     */
    @Bean
    @Primary
    public DataSource embeddedPostGreSqlDb() throws Exception {

        // Start an embedded PostGreSQL instance using the EmbeddedPostgres builder
        // and retrieve the corresponding DataSource.
        DataSource embeddedPostGreDS = EmbeddedPostgres.builder().start().getPostgresDatabase();

        logger.info("Embedded PostGreSQL DataSource configured successfully.");

        return embeddedPostGreDS;
    }


}
