package com.tutorial.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ModelAndViewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testHelloModelandViewOK() throws Exception {

        mockMvc.perform(
                get("/web/hello")
                        .queryParam("name", "budhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Belajar View")),
                content().string(Matchers.containsString("Hello budhi"))
        );

    }

    @Test
    void testHelloModelandViewKO() throws Exception {

        mockMvc.perform(
                post("/web/hello")
                        .queryParam("name", "budhi")
        ).andExpectAll(
                status().isMethodNotAllowed()
        );

    }


}