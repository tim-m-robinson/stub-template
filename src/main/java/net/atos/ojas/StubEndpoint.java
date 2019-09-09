package net.atos.ojas;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.security.SecureRandom;
import java.util.HashMap;


@Component
@Path("/dummy")
@Consumes(MediaType.WILDCARD)
public class StubEndpoint {

    @GET
    @Path("/echo/{msg}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response echo(@PathParam("msg") @NotNull String msg) {
      if (msg == null) msg = "";
      return Response.ok(msg + "\n").build();
    }

    @GET
    @Path("/reverse/{msg}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response reverse(@PathParam("msg") @NotNull String msg) {
      if (msg == null) msg = "";
      return Response.ok(new StringBuilder(msg).reverse().toString() + "\n").build();
    }

    @GET
    @Path("/time")
    @Produces(MediaType.TEXT_PLAIN)
    public Response time() {
      return Response.ok(ISODateTimeFormat.dateTime().print(new DateTime()) + "\n").build();
    }

    @POST
    @Path("/data")
    @Produces(MediaType.APPLICATION_JSON)
    @JacksonFeatures(serializationEnable =  { SerializationFeature.INDENT_OUTPUT })
    public Response data(@HeaderParam("X-CorrelationId") @NotNull String correlId)
    {
      return Response.ok(new Data()).build();
    }

    @XmlRootElement
    @XmlType(propOrder = {"id","tags","data"})
    private class Data {
      @XmlElement
      int id = new SecureRandom().nextInt();
      @XmlElement
      String[] tags = {"foo","bar","abc","def"};
      @XmlElement
      HashMap<String,Object> data = new HashMap();

      public Data() {
        super();
        data.put("a","b");
        data.put("c","d");
      }
    }

}
