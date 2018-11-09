package com.santander.gestaogastos;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe responsável pelas configurações de Segurança.
 * @author angelosatelite
 *
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2_console/**").permitAll()
        		.antMatchers("/resources/**").permitAll()
     			.antMatchers("/gestaogastos/**").permitAll()
     			.antMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
     			.antMatchers(HttpMethod.POST, "/roles").hasRole("ADMIN")
     			.antMatchers("/**").hasAnyRole("ADMIN", "CLIENTE");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}