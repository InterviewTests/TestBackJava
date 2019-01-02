package br.com.fellipeoliveira.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("client").password("secret").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .anonymous()
        .disable()
        .authorizeRequests()
        .antMatchers("/api-docs/**", "/users/**")
        .permitAll();

    http.antMatcher("/users/**").authorizeRequests().anyRequest().authenticated();

    http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/**")
        .access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST, "/**")
        .access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.PATCH, "/**")
        .access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.PUT, "/**")
        .access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.DELETE, "/**")
        .access("#oauth2.hasScope('write')")
        .and()
        .headers()
        .addHeaderWriter(
            (request, response) -> {
              response.addHeader("Access-Control-Allow-Origin", "*");
              if (request.getMethod().equals("OPTIONS")) {
                response.setHeader(
                    "Access-Control-Allow-Methods",
                    request.getHeader("Access-Control-Request-Method"));
                response.setHeader(
                    "Access-Control-Allow-Headers",
                    request.getHeader("Access-Control-Request-Headers"));
              }
            });
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }
}
