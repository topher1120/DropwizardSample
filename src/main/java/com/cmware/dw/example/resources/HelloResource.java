package com.cmware.dw.example.resources;

import com.cmware.dw.example.representations.HelloStatement;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Jersey Resource representing "Hello world" endpoint.
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource
{
    private final String template;
    private final String defaultName;
    private final AtomicLong counter = new AtomicLong();

    public HelloResource(String template, String defaultName)
    {
        this.template = template;
        this.defaultName = defaultName;
    }

    @GET
    @Timed
    public HelloStatement hello(@QueryParam("name")Optional<String> name){
        final String value = String.format(template, name.or(defaultName));
        return new HelloStatement(counter.incrementAndGet(), value);
    }
}
