package com.tutorial.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.mvc.entity.HelloRequest;
import com.tutorial.mvc.entity.HelloResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BodyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testrequestbodyHello() throws Exception {

        HelloRequest request = new HelloRequest();
        request.setName("budhi");

        // MockHttpServletRequestBuilder accept(String... mediaTypes) // media type yang harus di set uleh client ketika akses request http method
        // MockHttpServletRequestBuilder content(String content) // menyiapkan body data yang mau di post
        // byte[] writeValueAsBytes(Object value) // merubah object java ke format JSON
        // <T> T readValue(String content, Class<T> valueType) // konversi JSON menjadi object java

        mockMvc.perform(
                        post("/body/hello")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsBytes(request))
                ).andExpectAll(
                        status().isOk(),
                        header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.APPLICATION_JSON_VALUE)
                        ))
                .andExpect(result -> {
                    String responseBody = result.getResponse().getContentAsString();
                    HelloResponse response = objectMapper.readValue(responseBody, HelloResponse.class);
                    Assertions.assertEquals("Hello budhi", response.getHello());
                });

        // ResultActions andExpectAll(ResultMatcher... matchers)

        // andExpect(ResultMatcher matcher) // Lakukan sebuah harapan.
        // MockHttpServletResponse getResponse() // Return the hasil response.
        // String getContentAsString() throws UnsupportedEncodingException // Dapatkan konten badan respons sebagai String, menggunakan rangkaian karakter yang ditentukan untuk respons oleh aplikasi, baik melalui HttpServletResponse metode atau melalui parameter rangkaian karakter pada Content-Type.
    }

}