package dev.wellison.santander.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
        		.withUser("admin").password(encoder().encode("admin")).roles("ADMIN")
        		.and()
                .withUser("user").password(encoder().encode("user")).roles("USER");

    }

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/expense/**").hasAnyRole("USER","ADMIN")
            .antMatchers(HttpMethod.POST, "/expense/*").hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, "/expense/**").hasAnyRole("USER","ADMIN")
            .antMatchers(HttpMethod.GET, "/category/**").hasAnyRole("USER","ADMIN")
            .antMatchers(HttpMethod.POST, "/category/*").hasAnyRole("USER","ADMIN")
            .and()
            .csrf().disable()
            .formLogin().disable();
    }
}
