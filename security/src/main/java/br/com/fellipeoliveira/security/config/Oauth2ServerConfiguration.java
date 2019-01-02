package br.com.fellipeoliveira.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class Oauth2ServerConfiguration {

  private static final String SERVER_RESOURCE_ID = "oauth2-server";

  private static InMemoryTokenStore tokenStore = new InMemoryTokenStore();

  @Configuration
  @EnableAuthorizationServer
  @RequiredArgsConstructor
  protected static class AuthConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
          .authenticationManager(authenticationManager)
          .tokenStore(tokenStore)
          .approvalStoreDisabled();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients
          .inMemory()
          .withClient("client")
          .authorizedGrantTypes("refresh_token", "password", "client_credentials")
          .scopes("read", "write", "trust")
          .resourceIds(SERVER_RESOURCE_ID)
          .secret(passwordEncoder.encode("secret"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
      oauthServer.checkTokenAccess("permitAll()");
    }
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
