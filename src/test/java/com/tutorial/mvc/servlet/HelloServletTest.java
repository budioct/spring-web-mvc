package com.tutorial.mvc.servlet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloServletTest {

    @LocalServerPort
    private Long port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void servlet() {
        String response = restTemplate.getForObject("http://localhost:" + port + "/servlet/hello", String.class);

        Assertions.assertEquals("Hello from Servlet", response.trim());
    }

}
