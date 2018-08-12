package net.atos.ojas;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.arquillian.cube.CubeIp;
import org.jboss.arquillian.junit.Arquillian;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(Arquillian.class)
public class StubIntegrationTest extends StubBaseTest {

  @CubeIp(containerName = "int-test")
  private String cip;

  @Before
  public void setUp() throws Exception{
    RestAssured.port = 8443;
    RestAssured.baseURI = "https://"+cip;
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
