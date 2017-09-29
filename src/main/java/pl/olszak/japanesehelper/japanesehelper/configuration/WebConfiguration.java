package pl.olszak.japanesehelper.japanesehelper.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Slf4j
@Configuration
public class WebConfiguration implements ServletContextInitializer{

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

        return configuration;
    }
}
