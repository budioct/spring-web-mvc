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
class DateControllerTest {

    /**
     * Konversi Tipe Data
     * ● Kita tahu bahwa query parameter itu datanya adalah String
     * ● Namun jika kita membutuhkan datanya dalam bentuk tipe data lain, Spring bisa secara otomatis
     *   melakukan konversi tipe datanya menggunakan fitur Converter yang pernah kita bahas di materi Spring Config Properties
     */

    @Autowired
    MockMvc mockMvc;

    @Test
    void testDateRequestParam() throws Exception {

        // query param sesuai dengan converter
        // response sesuai dengan yang dibuat controller
        // MockHttpServletRequestBuilder queryParam(String name, String... values) // Tambahkan ke string kueri dan tambahkan juga ke request parameters map.

        mockMvc.perform(
                get("/date")
                        .queryParam("date", "2020-10-22")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Date : 20201022"))
        );

    }

    @Test
    void testDateRequestParamWithResponseBody() throws Exception {

        // query param sesuai dengan converter
        // response sesuai dengan yang dibuat controller

        mockMvc.perform(
                get("/datewithresponsebody")
                        .queryParam("date", "2020-10-22")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Date : 20201022"))
        );

    }


}