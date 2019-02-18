package br.com.adslima.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import br.com.adslima.service.impl.UserAuthenticationServiceImpl;



@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private static String REALM = "MICRO_SERVICE_OAUTH";

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private AuthorizationCodeServices authorizationCodeServices;


  @Autowired
  private UserAuthenticationServiceImpl userAuthenticationServiceImpl;

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Autowired
  private DataSource dataSource;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private TokenEnhancer tokenEnhancer;
  
  final static Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource).passwordEncoder(passwordEncoder);

  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

    endpoints.tokenStore(tokenStore).authorizationCodeServices(authorizationCodeServices)
        .userDetailsService(userAuthenticationServiceImpl)
        .authenticationManager(authenticationManager).tokenEnhancer(tokenEnhancer)
        .approvalStoreDisabled();
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.realm(REALM + "/client").passwordEncoder(passwordEncoder);
  }

  @Bean
  @Primary
  public AuthorizationServerTokenServices tokenServices() {
    DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setTokenStore(tokenStore);
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenEnhancer(tokenEnhancer);
    return tokenServices;
  }
}
