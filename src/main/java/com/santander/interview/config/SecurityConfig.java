package com.santander.interview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/expense-management/expenses").hasRole("ADMIN")
                .antMatchers("/expense-management/expense/**").hasRole("CLIENT")
                .antMatchers("/expense-management/categories").hasRole("CLIENT")
                .antMatchers("/expense-management/category/**").hasRole("CLIENT")
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
                .withUser("admin").password(passwordEncoder.encode("myadmin")).roles("ADMIN")
//                .withUser("admin").password("{noop}myadmin").roles("ADMIN")
                .and()
                .withUser("client").password(passwordEncoder.encode("theclient")).roles("CLIENT");

    }
}
