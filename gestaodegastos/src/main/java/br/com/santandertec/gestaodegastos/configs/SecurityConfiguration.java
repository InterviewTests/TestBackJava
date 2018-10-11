package br.com.santandertec.gestaodegastos.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.santandertec.gestaodegastos.daos.ClienteDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/*
	 * Classe responsável pelas configurações de Segurança do Spring.
	 ****************************************************************/
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/servico/gastos/**").permitAll()
			.antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
			.antMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN")
			.antMatchers("/roles/incluir").hasRole("ADMIN")
			.antMatchers("/**").hasAnyRole("ADMIN", "CLIENTE")
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
	}
	
	/* Método informa para o Spring utilizar a classe UsuarioDAO. 
	 * Método informa que a senha deve ser criptografada e será
	 * utilizada a criptografia BCrypt.
	 ****************************************************************/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(clienteDAO).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
