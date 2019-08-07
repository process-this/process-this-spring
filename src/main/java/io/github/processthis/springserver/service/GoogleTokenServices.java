/*  Copyright [2019] [Asher Bearce, Jeffery Franken, Matthew Jones, Jennifer Nevares-Diaz]
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
   limitations under the License.
*/

package io.github.processthis.springserver.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

/**
 * This class implements the necessary code for Google Sign-In services
 */
@Component
public class GoogleTokenServices implements ResourceServerTokenServices {

  @Value("${oauth.clientId}")
  private String clientId;

  private final AccessTokenConverter converter = new DefaultAccessTokenConverter();

  /**
   * This method loads the access token provided by a client accessing the server. It throws an
   * exception is the token was invalid or a different exception if the authentication process
   * failed for a different reason
   *
   * @throws AuthenticationException authentication failed
   * @throws InvalidTokenException the access token was invalid
   */
  @Override
  public OAuth2Authentication loadAuthentication(String accessToken)
      throws AuthenticationException, InvalidTokenException {
    try {
      HttpTransport transport = new NetHttpTransport();
      JacksonFactory jsonFactory = new JacksonFactory();
      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
          .setAudience(Collections.singletonList(clientId))
          .build();
      GoogleIdToken idToken = verifier.verify(accessToken);
      if (idToken != null) {
        Payload payload = idToken
            .getPayload(); // Payload may contain more useful info than we're using.
        // TODO Check user registry (if any) to see what roles should be granted.
        Collection<GrantedAuthority> grants =
            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        Authentication base =
            new UsernamePasswordAuthenticationToken(payload.getSubject(), accessToken, grants);
        OAuth2Request request = converter.extractAuthentication(payload).getOAuth2Request();
        return new OAuth2Authentication(request, base);
      } else {
        throw new BadCredentialsException(accessToken);
      }
    } catch (GeneralSecurityException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This method is a required override to any class implementing the ResourceServerTokenServices
   * interface
   */
  @Override
  public OAuth2AccessToken readAccessToken(String s) {
    return null;
  }
}



