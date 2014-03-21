package com.cmware.dw.example.resources;

import org.apache.commons.lang.StringUtils;
import com.cmware.dw.example.representations.HelloStatement;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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
@Api(value="/hello", description = "sample hello world endpoint")
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
    @ApiOperation(value = "Say hello", notes = "Hello notes")
    @Timed
    public HelloStatement hello(
            @ApiParam(value = "name to say hello to", required = false, defaultValue = "Stranger")
            @QueryParam("name")String name){
        final String value = String.format(template, StringUtils.defaultIfBlank(name, defaultName));
        return new HelloStatement(counter.incrementAndGet(), value);
    }
}
