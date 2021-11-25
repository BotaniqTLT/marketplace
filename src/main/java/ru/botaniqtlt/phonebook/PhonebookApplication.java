package ru.botaniqtlt.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.botaniqtlt.phonebook.console.ConsoleHelper;

@SpringBootApplication
public class PhonebookApplication {



	public static void main(String[] args) {
		SpringApplication.run(PhonebookApplication.class, args);
	}

	@Bean
	public ConsoleHelper consoleHelper(){
		return  new ConsoleHelper(System.in, System.out);
	}

}
