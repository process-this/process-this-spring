package io.github.processthis.springserver.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.processthis.springserver.BaseControllerTest;
import io.github.processthis.springserver.SpringServerApplicationTest;
import io.github.processthis.springserver.model.entity.UserProfile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(classes = SpringServerApplicationTest.class)
class UserProfileControllerTest extends BaseControllerTest {

  @Autowired
  protected UserProfileControllerTest(ObjectMapper mapper,
      WebApplicationContext context) {
    super(mapper, context);
  }

  @Test
  void getUserBadId() throws Exception {
    getMockMvc().perform(get("/users/XXXXXXXX").with(oauthTokenRequired()))
        .andExpect(status().isBadRequest());
  }


  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void getUsers() throws Exception {
    addUser("Al Sharpton", "authorized");
      getMockMvc().perform(get("/users").with(oauthTokenRequired()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$",hasSize(1)));
  }

  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void getUserById() throws Exception {
    StringBuilder builder = new StringBuilder("/users/");
    addUser("Sadio", "authtoot")
        .andDo(mvcResult -> {
          UserProfile userProfile = getMapper().readValue(mvcResult.getResponse().getContentAsString(), UserProfile.class);
          builder.append(userProfile.getId().toString());
        });
    getMockMvc().perform(get(builder.toString()).with(oauthTokenRequired())
    .contentType((MediaType.APPLICATION_JSON)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  void getUserSketches() {

  }



}