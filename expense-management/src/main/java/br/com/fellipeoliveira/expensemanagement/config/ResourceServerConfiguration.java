package br.com.fellipeoliveira.expensemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  private static final String SERVER_RESOURCE_ID = "oauth2-server";

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .antMatchers(
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**")
        .permitAll()
        .antMatchers("/rest/pub")
        .permitAll()
        .antMatchers("/**")
        .authenticated();
  }

  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenServices(tokenService()).resourceId(SERVER_RESOURCE_ID).stateless(true);
  }

  @Bean
  public RemoteTokenServices tokenService() {
    RemoteTokenServices tokenService = new RemoteTokenServices();
    tokenService.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
    tokenService.setClientId("client");
    tokenService.setClientSecret("secret");
    return tokenService;
  }
}
