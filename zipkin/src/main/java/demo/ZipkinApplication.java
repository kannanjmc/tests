package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

import com.github.kristofa.brave.LoggingSpanCollector;
import com.github.kristofa.brave.SpanCollector;

@SpringBootApplication
public class ZipkinApplication {

	@Bean
	public AlwaysSampler alwaysSampler() {
		return new AlwaysSampler();
	}

	// Use this for debugging (or if there is no Zipkin collector running on port 9410)
	@Bean
	@ConditionalOnProperty(value="sample.zipkin.enabled", havingValue="false", matchIfMissing=true)
	public SpanCollector spanCollector() {
		return new LoggingSpanCollector();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
}
