package com.cmware.dw.example;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cmware.dw.example.resources.HelloResource;

/**
 * Sample application object for the example application.
 */
public class ExampleApplication extends Application<ExampleAppConfiguration>
{
    public static void main(String[] args) throws Exception
    {
        new ExampleApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "Example Application";
    }

    @Override
    public void initialize(Bootstrap<ExampleAppConfiguration> bootstrap)
    {
        //bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(ExampleAppConfiguration configuration, Environment environment) throws Exception
    {
        environment.jersey().setUrlPattern("/service/*");
        final HelloResource resource = new HelloResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);
    }
}
