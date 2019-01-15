package com.santander.test.backend.bweninger.config;

import com.santander.test.backend.bweninger.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by BWeninger on 10/01/2019.
 */
@Configuration
public class JwtFilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(new JwtFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("jwtFilter");
        registration.setOrder(1);
        return registration;
    }
}
