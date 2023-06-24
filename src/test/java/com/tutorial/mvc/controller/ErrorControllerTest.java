package com.tutorial.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.mvc.entity.CreatePersonRequest;
import com.tutorial.mvc.entity.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ErrorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; // class yang digunakan untuk konversi object java ke format JSON

    @Test
    void testAPIPersonValidationErrorHandler() throws Exception {

        CreatePersonRequest person = new CreatePersonRequest();
        person.setMiddleName("oct");
        person.setHobbies(List.of("eating", "coding", "sleeping"));
        person.setSocialMedia(new ArrayList<>());
        person.getSocialMedia().add(new CreateSocialMediaRequest("twitter", "twitter.com/oct"));

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person))
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validator Error from : "))
        );

    }
}