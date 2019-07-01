package br.com.zup.way;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class FlywaySlaveInitializer {

    private DataSource dataSource;

    public FlywaySlaveInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void migrateFlyway() {
        Flyway flyway = new Flyway();

        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migration");
        flyway.migrate();

    }
}
