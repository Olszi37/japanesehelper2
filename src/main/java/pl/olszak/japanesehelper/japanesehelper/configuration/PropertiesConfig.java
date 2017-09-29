package pl.olszak.japanesehelper.japanesehelper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Bean
    ApplicationProperties applicationProperties(){
        return new ApplicationProperties();
    }
}
