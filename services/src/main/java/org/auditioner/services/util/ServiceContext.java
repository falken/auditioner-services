package org.auditioner.services.util;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ServiceContext {
    private ServiceContextConfiguration serviceConfiguration;


    public ServiceContext(ServiceContextConfiguration serviceConfiguration) {
        this.serviceConfiguration = serviceConfiguration;
    }

    public ServiceContextConfiguration getServiceConfiguration(){
        return serviceConfiguration;
    }

    public URI createUriFromPath(String path) {
        return UriBuilder.fromUri(serviceConfiguration.getHostNameRoot()).path(path).build();
    }

}