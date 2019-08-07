package io.github.processthis.springserver;

import static capital.scalable.restdocs.misc.AuthorizationSnippet.documentAuthorization;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.processthis.springserver.model.entity.UserProfile;
import java.util.Base64;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


public class BaseControllerTest {

  private static final String OAUTH_NOTICE =
      "OAuth2.0 bearer token required in Authorization Header.";

  private MockMvc mockMvc;
  private final ObjectMapper mapper;
  private final WebApplicationContext context;
  private final Random rng = new Random();

  protected  BaseControllerTest(ObjectMapper mapper, WebApplicationContext context) {
    this.mapper = mapper;
    this.context = context;


  }

  @BeforeEach
  protected void setUp(RestDocumentationContextProvider provider) {
    mockMvc = MockMvcBuilders.webAppContextSetup(context)
        .alwaysDo(JacksonResultHandlers.prepareJackson(mapper))
        .apply(
            documentationConfiguration(provider)
            .uris()
            .withScheme("http")
            .withHost("http://localhost:18181")// FIXME need to find out where we will host and use the address here.
            .withPort(443)
            .and()
            .snippets()
            .withDefaults(
                HttpDocumentation.httpRequest(),
                HttpDocumentation.httpResponse(),
                AutoDocumentation.requestHeaders(),
                AutoDocumentation.authorization(OAUTH_NOTICE),
                AutoDocumentation.pathParameters(),
                AutoDocumentation.requestParameters(),
                AutoDocumentation.requestFields(),
                AutoDocumentation.responseFields(),
                AutoDocumentation.description(),
                AutoDocumentation.methodAndPath(),
                AutoDocumentation.section()
            )
        )
        .alwaysDo(document("{class-name}/{method-name}",
            preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
        .build();

  }

  protected MockMvc getMockMvc() {
    return mockMvc;
  }

  protected ObjectMapper getMapper() {
   return mapper;
  }
  protected WebApplicationContext getContext() {
    return context;
  }

  protected RequestPostProcessor oauthTokenRequired() {
    return (request) -> {
      byte[] bytes = new byte[48];
      rng.nextBytes(bytes);
      request.addHeader("Authorization", String.format("Bearer %s",
          Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)));
      return documentAuthorization(request, OAUTH_NOTICE);
    // TODO find out bearer and authorization info
    };
  }

  public ResultActions addUser(String username, String authId) throws Exception {
    return getMockMvc().perform(
        post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .with(oauthTokenRequired())
            .content(String.format("{\"username\": \"%s\", \"authId\":\"%s\"}", username, authId))
    );
  }

  public   ResultActions addAndAttribute(String username, String auth, StringBuilder builder) throws Exception {
    builder.append("/users/");
    addUser(username, auth).andDo(mvcResult -> {
      UserProfile userProfile = getMapper().readValue(mvcResult.getResponse().getContentAsString(),UserProfile.class);
      builder.append(userProfile.getId().toString());
    });
    return getMockMvc().perform(get(builder.toString()).with(oauthTokenRequired()));
  }
}
