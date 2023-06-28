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

        // .header("X-API-KEY", "BUDHI")
        // header(keyResolver, idObjectParent / key)

        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY", "BUDHI")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("BUDHI Sample Partner"))
        );

    }

    @Test
    void testGetPartneFailed() throws Exception {

        // kita set key header dengan format yang salah. suapaya kita tahu Exception apa yang di tangkap
        // apakah dari object resolver yang tangkap atau dari spring

        mockMvc.perform(
                get("/partner/current")
                        .header("X-API-KEY-ASELOLE", "Salah")
        ).andExpectAll(
                status().isBadRequest()
        );

        /**
         * kita mencoba set key header yang salah
         * // exception dari resolver yang dibuat
         * jakarta.servlet.ServletException: Request processing failed: java.lang.RuntimeException: Unauthorized Exception
         *
         * // exception dari spring mvc
         * jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.tutorial.mvc.entity.Partner.getId()" because "partner" is null
         *
         */

    }

}