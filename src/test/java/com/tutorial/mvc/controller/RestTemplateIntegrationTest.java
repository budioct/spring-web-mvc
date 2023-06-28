package com.tutorial.mvc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateIntegrationTest {

    /**
     * Rest Template
     * ● Saat kita membuat aplikasi Web / RESTful API, kadang kita juga butuh memanggil/mengirim data
     *   ke server Web/RESTful API lainnya
     * ● Spring sudah menyediakan class bernama RestTemplate, yang bisa kita gunakan sebagai HTTP
     *   Client / RESTful Client
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
     *
     * Rest Template Builder
     * ● Untuk membuat RestTemplate, kita bisa menggunakan RestTemplateBuilder, yang secara otomatis
     *   sudah di buatkan sebagai Bean oleh Spring Boot
     * ● Sebelum membuat RestTemplate, kita bisa melakukan konfigurasi terlebih dahulu di RestTemplateBuilder
     * ● https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/client/RestTemplateBuilder.html
     *
     * Request & Response Entity
     * ● Untuk mengirim request, kita bisa menggunakan RestTemplate.exchange(), dimana kita perlu
     *   membuat RequestEntity
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/RequestEntity.html
     * ● Response dari RestTemplate adalah object ResponseEntity
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
     * ● Pada kasus server mengembalikan data JSON, kita bisa otomatis melakukan konversi menjadi Object dengan bantuan Jackson secara otomatis
     */


    @LocalServerPort
    private Long port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void addTodosIntegrationTest(){

        String url = "http://localhost:" + port + "/todos";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        MultiValueMap<String, Object> from = new LinkedMultiValueMap<>();
        from.add("todo", "Belajar Spring WebMVC");

        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(from, headers, HttpMethod.POST, URI.create(url));

        ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertTrue(response.getBody().contains("Belajar Spring WebMVC"));

    }

    @Test
    void getTodosIntegrationTest(){

        String url = "http://localhost:" + port + "/todos";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        RequestEntity<Object> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));

        ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {});

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Belajar Spring WebMVC", response.getBody().get(0));
    }



}
