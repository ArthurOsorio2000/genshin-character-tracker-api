package GenshinCharacterTracker.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		UI.createTerminal();
		SpringApplication.run(Main.class, args);
	}
}


