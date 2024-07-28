package ec.edu.uce.interfaz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
public class InterfazApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterfazApplication.class, args);
	}

}
