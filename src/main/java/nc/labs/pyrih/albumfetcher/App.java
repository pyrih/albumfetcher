package nc.labs.pyrih.albumfetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class App {

    private static final long SECONDS = 1000L;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        Duration timeout = Duration.ofSeconds(SECONDS);
        return builder
                .setConnectTimeout(timeout)
                .setReadTimeout(timeout)
                .build();
    }

}
