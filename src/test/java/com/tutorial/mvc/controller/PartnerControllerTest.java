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
class PartnerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetPartneSuccess() throws Exception {

        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY", "SAMPLE")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("SAMPLE Sample Partner"))
        );

    }

    @Test
    void testGetPartneFailed() throws Exception {

        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY-ASELOLE", "Salah")
        ).andExpectAll(
                status().isBadRequest()
        );

        /**
         * kita mencoba set key header yang salah
         * result exception: kena error resolver tandanya berhasil
         * jakarta.servlet.ServletException: Request processing failed: java.lang.RuntimeException: Unauthorized Exception
         */

    }

}