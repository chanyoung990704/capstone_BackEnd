package unit.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	//CORS문제를 해결하기 위한 Bean
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("*")
						.allowedHeaders("")
						.allowedHeaders("Authorization", "Content-Type")
						.exposedHeaders("Authorization")
						.allowCredentials(true);
			}
		};
	}
}
