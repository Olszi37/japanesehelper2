package pl.olszak.japanesehelper.japanesehelper.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Slf4j
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{

    @Value("${endpoints.cors.allowed-methods}")
    private String[] methods;
    @Value("${endpoints.cors.allowed-headers}")
    private String[] headers;
    @Value("${endpoints.cors.allowed-origins}")
    private String[] origins;
    @Value("${endpoints.cors.allow-credentials}")
    private boolean allowCredentials;
    @Value("${endpoints.cors.max-age}")
    private Long maxAge;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/jhelper/**")
                .allowedHeaders(headers)
                .allowedMethods(methods)
                .allowedOrigins(origins)
                .allowCredentials(allowCredentials)
                .maxAge(1800);
    }
}
