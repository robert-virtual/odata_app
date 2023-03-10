package com.example.odata_app.config;

import jakarta.persistence.EntityManagerFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;
import org.apache.olingo.odata2.core.rest.app.ODataApplication;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/odata")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(CarsODataJPAServiceFactory serviceFactory, EntityManagerFactory emf){
        ODataApplication app = new ODataApplication();
        app.getClasses()
                .forEach(c->{
                    if (!ODataRootLocator.class.isAssignableFrom(c))
                        register(c);
                });
        register(new CarsRootLocator(serviceFactory));
        register(new EntityManagerFilter(emf));

    }

}
