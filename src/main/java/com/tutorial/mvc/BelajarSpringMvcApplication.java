package com.tutorial.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@ServletComponentScan // akan scan project dengan target @WebServlet java web servlet
public class BelajarSpringMvcApplication {

	// membuat RestTemplate (object yang fokus ke clinet)
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		// RestTemplateBuilder // object untuk membuat RestTemplate
		// RestTemplateBuilder setConnectTimeout(Duration connectTimeout) // set batas waktu koneksi pada file ClientHttpRequestFactory.
		// RestTemplateBuilder setReadTimeout(Duration readTimeout) // set  batas waktu baca pada file ClientHttpRequestFactory.
		// RestTemplate build() //
		return builder
				.setConnectTimeout(Duration.ofSeconds(2L))
				.setReadTimeout(Duration.ofSeconds(2L))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(BelajarSpringMvcApplication.class, args);
	}

}
