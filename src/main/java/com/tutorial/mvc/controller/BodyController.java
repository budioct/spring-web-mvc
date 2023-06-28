package com.tutorial.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.mvc.entity.HelloRequest;
import com.tutorial.mvc.entity.HelloResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class BodyController {

    /**
     * Request Body
     * ● Saat kita membuat aplikasi web berupa RESTful API, kadang kita ingin mengirim data lewat
     *   Request Body dalam bentuk format data seperti JSON, XML, dan sejenisnya
     * ● Spring bisa digunakan untuk membaca data Request Body secara mudah, cukup menggunakan
     *   annotation @RequestBody
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestBody.html
     */

    @Autowired
    ObjectMapper objectMapper; // class konversi object to JSON / XML

    @PostMapping(path = "/body/hello",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String body(@RequestBody String requestBody) throws JsonProcessingException {

        // @RequestBody // handle request client seperti format JSON, XML,  dll

        HelloRequest request = objectMapper.readValue(requestBody, HelloRequest.class); // <T> T readValue(String content, Class<T> valueType) // konversi JSON menjadi object java

        HelloResponse response = new HelloResponse();
        response.setHello("Hello " + request.getName());

        log.info("Response: {}", response.getHello());

        return objectMapper.writeValueAsString(response); // String writeValueAsString(Object value) // konversi menjadi JSON

        /**
         * endpoint: localhost:8080/body/hello
         * request body:
         * {
         *   "name" : "budhi"
         * }
         *
         * response body. Status: 200 OK
         * {
         *   "hello": "Hello budhi"
         * }
         */

    }

}
