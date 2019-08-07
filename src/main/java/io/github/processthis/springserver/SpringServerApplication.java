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


package io.github.processthis.springserver;




import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * This class defines the application a Spring server application. It includes the main method and
 * methods defining the the http protocol and Google-Sign In identofication
 */
@SpringBootApplication
@EnableWebSecurity
@EnableResourceServer
public class SpringServerApplication extends ResourceServerConfigurerAdapter {

  @Value("${oauth.clientId}")
  private String clientId;


  /**
   * This is the main method. It is a required entry-point for any Java application to run. This one
   * calls the SpringServerApplication class that houses is
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringServerApplication.class, args);
  }

  /**
   * This method refines the resource Id as the the client Id parameter
   */
  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(clientId);
  }

  /**
   * This method defines what roles may access the server  and defines what rights those roles have
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().anyRequest().anonymous();
//    http.authorizeRequests().anyRequest().hasRole("USER");
  }

}
