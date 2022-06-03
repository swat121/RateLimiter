package algorithm.RateLimiter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application")
public class Config {
    int maxRequestPerMin;
}
