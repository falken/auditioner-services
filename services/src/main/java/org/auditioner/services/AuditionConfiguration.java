package org.auditioner.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.auditioner.services.util.ServiceContextConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AuditionConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @JsonProperty("serviceContext")
    private ServiceContextConfiguration serviceContext = new ServiceContextConfiguration();


    public ServiceContextConfiguration getServiceContext() {
        return serviceContext;
    }

    public void setServiceContext(ServiceContextConfiguration serviceContext) {
        this.serviceContext = serviceContext;
    }
}
