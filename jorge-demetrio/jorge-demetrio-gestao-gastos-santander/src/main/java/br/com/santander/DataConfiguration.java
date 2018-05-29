/**
 *
 */
package br.com.santander;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * 
 * @author Jorge Demetrio
 * @version 1.0
 * @since 26 de mai de 2018
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class DataConfiguration {

	private final Logger LOGGER = Logger.getLogger(DataConfiguration.class);

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		LOGGER.debug("Pegando o DataSoruce.");
		DriverManagerDataSource ds = null;
		try {
			ds = new DriverManagerDataSource();
			ds.setDriverClassName(environment.getRequiredProperty("banco.drive"));
			ds.setUrl(environment.getRequiredProperty("banco.url"));
			ds.setUsername(environment.getRequiredProperty("banco.usuario"));
			ds.setPassword(environment.getRequiredProperty("banco.senha"));

		} catch (final Exception ne) {
			LOGGER.error(ne.getMessage(), ne);
			throw new RuntimeException(ne);
		}
		return ds;

	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		LOGGER.debug("Chamando o hibernateProperties.");
		final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setDatabasePlatform(environment.getRequiredProperty("hibernate.dialect"));
		adapter.setShowSql("true".equalsIgnoreCase(environment.getRequiredProperty("hibernate.show_sql")));
		adapter.setGenerateDdl("true".equalsIgnoreCase(environment.getRequiredProperty("hibernate.hbm2ddl.auto")));

		return adapter;
	}

}
