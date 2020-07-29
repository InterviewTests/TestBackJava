/*
 * © 2020 Altran - Copyright - Todos os direitos reservados.
 */
package br.com.santander.testbackjava.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;

/**
 * Classe responsável pela configuração do Cassandra.
 *
 * @author Altran - jabes
 * @since 28 de jul de 2020 16:48:31
 * @version x.x
 */
@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	/**
	 * Atributo keyspaceName
	 */
	@Value("${spring.data.cassandra.keyspace-name}")
	protected String keyspaceName;
	
//	/**
//	 * Atributo contactPoints
//	 */
//	@Value("${spring.data.cassandra.contact-points}")
//	protected String contactPoints;

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keyspaceName);

		return Arrays.asList(specification);
	}

	@Override
	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(keyspaceName));
	}

	@Override
	protected String getKeyspaceName() {
		return keyspaceName;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "br.com.santander.testbackjava.model.entity" };
	}
	
	@Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean bean = super.cluster();
        bean.setContactPoints(getContactPoints());
        return bean;
    }
	
	protected String getContactPoints() {
		return CassandraClusterFactoryBean.DEFAULT_CONTACT_POINTS;
	}

}
