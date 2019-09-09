package net.atos.ojas;

import io.restassured.RestAssured;

import org.arquillian.cube.CubeIp;
import org.arquillian.cube.openshift.api.OpenShiftDynamicImageStreamResource;
import org.arquillian.cube.openshift.api.Template;
import org.arquillian.cube.openshift.api.TemplateParameter;
import org.arquillian.cube.openshift.impl.enricher.AwaitRoute;
import org.arquillian.cube.openshift.impl.enricher.RouteURL;
import org.arquillian.cube.openshift.impl.requirement.RequiresOpenshift;
import org.arquillian.cube.requirement.ArquillianConditionalRunner;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

//@RunWith(Arquillian.class)
//@OpenShiftDynamicImageStreamResource(name = "stub-template", image = "10.0.2.15:5000/stub-template:1.0-SNAPSHOT", version="1.0-SNAPSHOT")
@Category(RequiresOpenshift.class)
@RequiresOpenshift
@RunWith(ArquillianConditionalRunner.class)
//@Template(url = "classpath:deployment.yml",
//        parameters = @TemplateParameter(name = "RESPONSE", value = "Hello from Arquillian Template"))
public class StubIntegrationTest extends StubBaseTest {

  @RouteURL("stub-template")
  //@AwaitRoute
  private URL url;

  @Before
  public void setUp() throws Exception{
    System.out.println("URL: "+url);

    RestAssured.port = url.getPort();
    RestAssured.baseURI = "https://"+url.getHost();
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
