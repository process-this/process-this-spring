package io.github.processthis.springserver.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.processthis.springserver.BaseControllerTest;
import io.github.processthis.springserver.SpringServerApplicationTest;
import io.github.processthis.springserver.model.entity.Sketch;
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
class SketchControllerTest extends BaseControllerTest {

  @Autowired
  protected SketchControllerTest(ObjectMapper mapper,
      WebApplicationContext context) {
    super(mapper, context);
  }


  @Test
  void addSketch() throws Exception {
    StringBuilder builder = new StringBuilder();
    addUser("Billy","isauth")
    .andDo(mvcResult -> {
      UserProfile userProfile = getMapper().readValue(mvcResult.getResponse().getContentAsString(), UserProfile.class);
      builder.append(userProfile.getId().toString());
    });
    String temp = builder.toString();
    getMockMvc().perform(post("users/{temp}/sketches",temp));
  }

  @Test
  void getUserSketches() throws Exception {
    StringBuilder builder = new StringBuilder();
    addUser("Billy","isauth")
        .andDo(mvcResult -> {
          UserProfile userProfile = getMapper().readValue(mvcResult.getResponse().getContentAsString(), UserProfile.class);
          builder.append(userProfile.getId().toString());
        });
    String temp = builder.toString();
    getMockMvc().perform(post("users/{temp}/sketches",temp))
        .andDo(mvcResult -> {
          Sketch sketch = getMapper().readValue(mvcResult.getResponse().getContentAsString(),Sketch.class);
          builder.replace(0,temp.length(),sketch.getId().toString());
        });
    String temp2 = builder.toString();
    getMockMvc().perform(get("users/{temp}/sketches/{temp2}", temp, temp2));

  }



}