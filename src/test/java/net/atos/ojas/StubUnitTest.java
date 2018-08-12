package net.atos.ojas;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = StubUnitTest.Setup.class)
public class StubUnitTest extends StubBaseTest {

  @Value("${local.server.port}")
  int port;

  @Before
  public void setUp() throws Exception{
    RestAssured.port = port;
    RestAssured.baseURI = "https://localhost";
    RestAssured.trustStore("ssl/truststore.p12", "password");

    // This config option disables all SSL validation checking
    //RestAssured.useRelaxedHTTPSValidation();
  }

  ///
  /// All Tests Defined in BaseTest class
  ///



  public static class Setup implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      //*******************************************
      // Use this option to disable SSL for testing
      //*******************************************
      /*
      EnvironmentTestUtils.addEnvironment("test", configurableApplicationContext.getEnvironment(),
        "server.ssl.enabled=false"
      );
      */
    }
  }

}
