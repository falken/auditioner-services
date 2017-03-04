package org.auditioner.services;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.auditioner.services.family.FamilyDAO;
import org.auditioner.services.family.FamilyResource;
import org.auditioner.services.family.member.FamilyMemberDAO;
import org.auditioner.services.family.member.FamilyMemberResource;
import org.auditioner.services.production.AuditionNumberGenerator;
import org.auditioner.services.production.ProductionDAO;
import org.auditioner.services.production.ProductionResource;
import org.auditioner.services.production.member.ProductionMemberDAO;
import org.auditioner.services.production.member.ProductionMemberResource;
import org.auditioner.services.family.member.FamilyMemberProductionResource;
import org.auditioner.services.util.ServiceContext;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class AuditionApplication extends Application<AuditionConfiguration>
{
    @Override
    public void initialize(Bootstrap<AuditionConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<AuditionConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    AuditionConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(AuditionConfiguration configuration, Environment environment) throws Exception {

        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Add URL mapping
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.TIMING_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_EXPOSE_HEADERS_HEADER, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        DBIFactory dbiFactory = new DBIFactory();

        ServiceContext context = new ServiceContext(configuration.getServiceContext());

        final DBI jdbi = dbiFactory.build(environment, configuration.getDataSourceFactory(), "auditioner-connection");

        final FamilyDAO familyDAO = jdbi.onDemand(FamilyDAO.class);
        environment.jersey().register(new FamilyResource(context,familyDAO));

        final FamilyMemberDAO familyMemberDAO = jdbi.onDemand(FamilyMemberDAO.class);
        environment.jersey().register(new FamilyMemberResource(context,familyMemberDAO));

        final ProductionDAO productionDAO = jdbi.onDemand(ProductionDAO.class);
        environment.jersey().register(new ProductionResource(context,productionDAO));

        final ProductionMemberDAO productionMemberDAO = jdbi.onDemand(ProductionMemberDAO.class);
        environment.jersey().register(new FamilyMemberProductionResource(context, productionMemberDAO));
        environment.jersey().register(new ProductionMemberResource(context, productionMemberDAO, new AuditionNumberGenerator(productionDAO), familyMemberDAO));
    }
}
