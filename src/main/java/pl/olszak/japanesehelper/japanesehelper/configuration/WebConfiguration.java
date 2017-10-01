package pl.olszak.japanesehelper.japanesehelper.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.List;

@Slf4j
@Configuration
public class WebConfiguration implements ServletContextInitializer{

    @Value("${endpoints.cors.allowed-methods}")
    private List<String> methods;
    @Value("${endpoints.cors.allowed-headers}")
    private List<String> headers;
    @Value("${endpoints.cors.allowed-origins}")
    private List<String> origins;
    @Value("${endpoints.cors.allow-credentials}")
    private boolean allowCredentials;
    @Value("${endpoints.cors.max-age}")
    private Long maxAge;


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = getCorsConfiguration();
        if (configuration.getAllowedOrigins() != null && !configuration.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/jhelper/**", configuration);
        }
        return new CorsFilter(source);
    }

    private CorsConfiguration getCorsConfiguration(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(headers);
        configuration.setAllowCredentials(allowCredentials);
        configuration.setAllowedOrigins(origins);
        configuration.setAllowedMethods(methods);
        configuration.setMaxAge(maxAge);
        return configuration;
    }
}
