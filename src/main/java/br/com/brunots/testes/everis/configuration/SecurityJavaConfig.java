package br.com.brunots.testes.everis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/gastos/cadastrar").hasRole("CREDENCIADO")
		.antMatchers("/gastos/listar").hasRole("USER")
		.antMatchers("/gastos/{gastoId}").hasRole("USER")
		.antMatchers("/users").permitAll()
		.antMatchers("/users/new").permitAll()
		.and().httpBasic();
	}

	@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(
        		User.withUsername("admin")
        		.password(encoder().encode("adminPaSS"))
        		.roles("USER")
        		.build());
        inMemoryUserDetailsManager.createUser(
        		User.withUsername("credenciado")
        		.password(encoder().encode("credenciadoPaSS"))
        		.roles("CREDENCIADO").build());
		return inMemoryUserDetailsManager;
    }

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}