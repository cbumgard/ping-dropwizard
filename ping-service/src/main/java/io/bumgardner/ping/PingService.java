package io.bumgardner.ping;

import io.bumgardner.ping.config.*;
import io.bumgardner.ping.daos.*;
import io.bumgardner.ping.models.*;
import io.bumgardner.ping.resources.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Optional;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.dropwizard.Service;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingService extends Service<PingConfiguration> {
    private static final Logger LOG = LoggerFactory.getLogger(PingService.class);

    public static void main(String[] args) throws Exception {
        new PingService().run(args);
    }

    private final HibernateBundle<PingConfiguration> hibernateBundle = new HibernateBundle<PingConfiguration>(
            
            User.class,
            Void.class
        ) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(PingConfiguration configuration) {
            return configuration.getDatabaseConfiguration();
        }
    };

    @Override
    public void initialize(Bootstrap<PingConfiguration> bootstrap) {
        bootstrap.setName("ping");
        bootstrap.addBundle(new AssetsBundle("/assets/app/", "/", "index.html"));
        bootstrap.addBundle(hibernateBundle);
        ObjectMapperFactory mapperFactory = bootstrap.getObjectMapperFactory();
        mapperFactory.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void run(PingConfiguration configuration,
                    Environment environment) throws Exception {
        
        environment.addResource(new PingResource());
        
        environment.addResource(new UserResource(
            new UserDAO(hibernateBundle.getSessionFactory())));
    }
}
