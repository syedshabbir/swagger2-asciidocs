package gradle.swagger.docs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContentMapperHealthCheckEndpoint extends HealthEndpoint
{
    private static final Logger LOG =
            LoggerFactory.getLogger(ContentMapperHealthCheckEndpoint.class);

    private Map<String, HealthIndicator> healthIndicators;

    public ContentMapperHealthCheckEndpoint(final HealthAggregator healthAggregator,
                                            final Map<String, HealthIndicator> healthIndicators)
    {
        super(healthAggregator, healthIndicators);
        this.healthIndicators = healthIndicators;
    }

    @Override
    public Health invoke()
    {
        Health health = super.invoke();
        if (health.getStatus().equals(Status.UP))
        {
            return Health.status(new Status("healthcheck_ok", health.getDetails().toString())).build();
        }
        else
        {
            LOG.warn("Not all the health indicators are up");
            Health h = Health.status(new Status("DOWN", health.getDetails().toString())).build();
            healthIndicators.keySet().stream().forEach(
                    hi -> LOG.warn(
                            String.format(
                                    "Indicator %s: Status %s (Details %s)", hi,
                                    healthIndicators.get(hi).health().getStatus(),
                                    healthIndicators.get(hi).health().getDetails())));
            return h;
        }
    }

}
