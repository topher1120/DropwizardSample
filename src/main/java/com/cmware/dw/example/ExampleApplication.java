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
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
        // When using multiple AssetsBundle objects, you MUST set the asset name for the subsequent bundles.
        // Otherwise, the last bundle wins and gets mapped to all the paths of the previous bundles with
        // the same name.
        bootstrap.addBundle(new AssetsBundle("/META-INF/resources/webjars", "/webjars", "index.htm", "WebjarsAssets"));
    }

    @Override
    public void run(ExampleAppConfiguration configuration, Environment environment) throws Exception
    {
        environment.jersey().setUrlPattern("/service/*");
        final HelloResource resource = new HelloResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);
    }
}
