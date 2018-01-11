package pl.olszak.japanesehelper.japanesehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("pl.olszak.japanesehelper.japanesehelper.domain")
@ComponentScan("pl.olszak.japanesehelper.japanesehelper")
@EnableJpaRepositories("pl.olszak.japanesehelper.japanesehelper.repository")
public class JapanesehelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JapanesehelperApplication.class, args);
	}
}
