package santander.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import santander.api.domain.Greetings;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class Application {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();


	@RequestMapping("/")
	public String home() {
		return "Rest API";
	}

	@RequestMapping("/greeting")
	public Greetings greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greetings(counter.incrementAndGet(),
				String.format(template, name));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}