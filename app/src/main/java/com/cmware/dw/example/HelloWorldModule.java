package com.cmware.dw.example;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import javax.inject.Named;

/**
 * Guice module for binding.
 */
public class HelloWorldModule extends AbstractModule
{
    @Override
    protected void configure()
    {

    }

    @Provides
    @Named("helloTemplate")
    public String provideHelloTemplate(ExampleAppConfiguration config){
        return config.getTemplate();
    }

    @Provides
    @Named("helloDefault")
    public String provideHelloDefault(ExampleAppConfiguration config){
        return config.getDefaultName();
    }
}
