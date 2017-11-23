package pl.olszak.japanesehelper.japanesehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class JapanesehelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JapanesehelperApplication.class, args);
	}
}
