package com.cmware.dw.example;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.cmware.dw.example.resources.HelloResource;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

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
        // When using multiple AssetsBundle objects, you MUST set the asset name for the subsequent bundles.
        // Otherwise, the last bundle wins and gets mapped to all the paths of the previous bundles with
        // the same name.
        bootstrap.addBundle(new AssetsBundle("/META-INF/resources/webjars", "/webjars", "index.htm", "WebjarsAssets"));

        //add swagger to root
        bootstrap.addBundle(new AssetsBundle("/META-INF/resources/webjars/swagger-ui/2.0.12", "/", "index.html", "swaggerAssets"));
    }

    @Override
    public void run(ExampleAppConfiguration configuration, Environment environment) throws Exception
    {
        addSwaggerConfig(environment);

        environment.jersey().setUrlPattern("/service/*");
        final HelloResource resource = new HelloResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);
    }

    private void addSwaggerConfig(Environment environment)
    {
        environment.jersey().register(new ApiListingResourceJSON());

        // Swagger providers
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner());

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader());

        // Set the swagger config options
        SwaggerConfig config = ConfigFactory.config();
        config.setApiVersion("1.0");
        config.setBasePath("http://localhost:8080/service");
    }
}
