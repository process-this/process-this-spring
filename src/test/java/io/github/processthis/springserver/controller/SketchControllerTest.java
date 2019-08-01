package io.github.processthis.springserver.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.processthis.springserver.BaseControllerTest;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(classes = UserProfileController.class)
class SketchControllerTest extends BaseControllerTest {


  protected SketchControllerTest(ObjectMapper mapper,
      WebApplicationContext context, Random rng) {
    super(mapper, context, rng);
  }



}