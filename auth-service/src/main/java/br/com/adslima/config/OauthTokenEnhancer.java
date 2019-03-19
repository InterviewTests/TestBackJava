package br.com.adslima.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import br.com.adslima.entity.Users;
import br.com.adslima.service.impl.UserServiceImpl;

@Component
public class OauthTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		String userName = authentication.getName();
		Users user = userServiceImpl.findByUsername(userName);
		final Map<String, Object> additionalInfo = new HashMap<>();
		additionalInfo.put("name", user.getFirstName());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}

}
