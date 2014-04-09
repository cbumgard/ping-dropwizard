package io.bumgardner.ping.resources;

import com.yammer.dropwizard.jersey.params.BooleanParam;
import com.yammer.dropwizard.jersey.params.DateTimeParam;
import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class PingResource {

    private static final Logger LOG = LoggerFactory.getLogger(PingResource.class);

    
    @Path("{message}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed 
    public String ping(
        
        @PathParam("message") String message
        ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Received parameters:\n");
        
        sb.append("message=");
        sb.append(message);
        sb.append("\n");
        
        return sb.toString();
    }
    
}
