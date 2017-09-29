package pl.olszak.japanesehelper.japanesehelper.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String secret;

    private long tokenValidityInSeconds;
}
