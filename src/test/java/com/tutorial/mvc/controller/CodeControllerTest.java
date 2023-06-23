package com.tutorial.mvc.controller;

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
class CodeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testDeleteWithStatusCode202() throws Exception {

        // MockHttpServletRequestBuilder delete(URI uri) // menerima request endpoint dengan method delete
        // ResultMatcher isAccepted() // harapanya status code sama dengan response endpoint 202 Accepted

        mockMvc.perform(
                delete("/products/3443")
        ).andExpectAll(
                status().isAccepted()
        );

    }

}