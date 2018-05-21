package br.com.resource.testbackjava.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.KeyspaceIdentifier;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DataCenterReplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@PropertySource(value = { "classpath:database.properties" })
@EnableCassandraRepositories("br.com.resource.testbackjava")
public class CassandraConfig extends AbstractCassandraConfiguration {

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfig.class);

	@Value("${spring.data.cassandra.keyspace-name}")
	private String KEYSPACE;
	@Value("${spring.data.cassandra.super-user}")
	private static final String USERNAME = "cassandra";
	@Value("${spring.data.cassandra.super-user-password}")
	private static final String PASSWORD = "cassandra";
	@Value("${spring.data.cassandra.contact-points}")
	private String HOSTNAME;
	@Value("${spring.data.cassandra.port}")
	private String port;

	@Value("${spring.data.cassandra.schema-action}")
	private String schema_action;

	@Bean
	@Override
	public CassandraCqlClusterFactoryBean cluster() {
		CassandraCqlClusterFactoryBean bean = new CassandraCqlClusterFactoryBean();
		bean.setKeyspaceCreations(getKeyspaceCreations());
		bean.setContactPoints(HOSTNAME);
		bean.setUsername(USERNAME);
		bean.setPassword(PASSWORD);
		return bean;
	}

	@Override
	public SchemaAction getSchemaAction() {
		SchemaAction action = SchemaAction.CREATE_IF_NOT_EXISTS;
		try {
			if (this.schema_action != null) {
				action = SchemaAction.valueOf(schema_action.toUpperCase());
			}
		} catch (Exception e) {
			LOGGER.error("SchemaAction não identificado: " + this.schema_action, e);
		}
		return action;
	}

	@Override
	protected String getKeyspaceName() {
		return KEYSPACE;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "br.com.resource.testbackjava.data" };
	}

	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
		createKeyspaceSpecifications.add(getKeySpaceSpecification());
		return createKeyspaceSpecifications;
	}

	// Below method creates "my_keyspace" if it doesnt exist.
	private CreateKeyspaceSpecification getKeySpaceSpecification() {
		KeyspaceIdentifier id = KeyspaceIdentifier.of(KEYSPACE);
		CreateKeyspaceSpecification keyspace = CreateKeyspaceSpecification.createKeyspace(id);
		DataCenterReplication dcr = DataCenterReplication.of("dc1", 3L);
		keyspace.ifNotExists(true);
		CreateKeyspaceSpecification.createKeyspace(KEYSPACE).withNetworkReplication(dcr);
		return keyspace;
	}

}