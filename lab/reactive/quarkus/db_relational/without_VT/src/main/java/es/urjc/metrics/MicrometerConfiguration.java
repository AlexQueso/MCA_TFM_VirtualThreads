package es.urjc.metrics;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import java.util.List;

@ApplicationScoped
public class MicrometerConfiguration {

    @Produces
    @Singleton
    public MeterFilter configureAllRegistries() {
        return MeterFilter.commonTags(List.of(Tag.of("application", "quarkus-reactive")));
    }

}
