package br.com.santandertec.gestaodegastos.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	/*
	  Classe responsável pela configuração do banco, usuário e senha do banco dados e de propriedades do hibernate.
	  Neste classe, teremos o método que será gerenciado pelo Spring e criará o "EntityManager" usado no DAO. 
	*/
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFacotry(DataSource dataSource, Properties aditionalPropertiesOfHibernate) {

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaProperties(aditionalPropertiesOfHibernate);
		factoryBean.setPackagesToScan("br.com.santandertec.gestaodegastos.models");

		return factoryBean;
	}

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("kaigor@1008");
		dataSource.setUrl("jdbc:mysql://localhost:3306/gestaodegastos");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		return dataSource;
	}
	
	@Bean
	public Properties aditionalPropertiesOfHibernate() {

		Properties props = new Properties();

		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");

		return props;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
}
