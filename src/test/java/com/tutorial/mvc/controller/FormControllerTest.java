package com.tutorial.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
@SpringBootTest
@AutoConfigureMockMvc
class FormControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testFormHelloConsume() throws Exception {

        // ini akan error saat kita input form karena kita belum menambahkan conten-Type nyaa
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Setel the 'Tipe-Konten' dari permintaan.
        // MockHttpServletRequestBuilder queryParam(String name, String... values) // Tambahkan ke string kueri dan tambahkan juga ke request parameters map.

        mockMvc.perform(
                post("/form/hello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("name", "budhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello budhi"))
        );

    }

    @Test
    void testFormHelloGuest() throws Exception {

        // ini akan error saat kita input form karena kita belum menambahkan conten-Type nyaa
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Setel the 'Tipe-Konten' dari permintaan.
        // MockHttpServletRequestBuilder queryParam(String name, String... values) // Tambahkan ke string kueri dan tambahkan juga ke request parameters map.

        // note karena queryParam() tidak boleh null pada pram values jadi kita harus makesencen dengan false nya suapaya return data false bisa keluar
        mockMvc.perform(
                post("/form/hello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("name", "Guest")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guest"))
        );

    }

    @Test
    void testFormHelloProduce() throws Exception {

        // ini akan error saat kita input form karena kita belum menambahkan conten-Type nyaa
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Setel the 'Tipe-Konten' dari permintaan.
        // MockHttpServletRequestBuilder queryParam(String name, String... values) // Tambahkan ke string kueri dan tambahkan juga ke request parameters map.
        // HeaderResultMatchers header() // Akses ke response header assertions
        // ResultMatcher string(String name, Matcher<? super String> matcher) // Tegaskan nilai utama dari tajuk respons dengan Hamcrest String yang diberikan Matcher.

        mockMvc.perform(
                post("/form/hello")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("name", "budhi")
        ).andExpectAll(
                status().isOk(),
                header().string(HttpHeaders.CONTENT_TYPE, Matchers.containsString(MediaType.TEXT_HTML_VALUE)),
                content().string(Matchers.containsString("Hello budhi"))
        );

    }

    @Test
    void testFormPerson() throws Exception {

        // ini kita akan get request parameter atau query parameter servlet
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Setel the 'Tipe-Konten' dari permintaan.
        // MockHttpServletRequestBuilder param(String name, String... values) // Tambahkan parameter permintaan ke MockHttpServletRequest.getParameterMap().

        mockMvc.perform(
                post("/form/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("name", "budhi")
                        .param("birthDate", "2023-10-10")
                        .param("address", "indonesia")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create Person with name : budhi" +
                        ", birthDate: 20231010" +
                        ", address : indonesia"
                        ))
        );

    }

    @Test
    void testFormPersonBadRequest() throws Exception {

        // ini kita akan get request parameter atau query parameter servlet
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Setel the 'Tipe-Konten' dari permintaan.
        // MockHttpServletRequestBuilder param(String name, String... values) // Tambahkan parameter permintaan ke MockHttpServletRequest.getParameterMap().

        // kita buat badrquest dengan code status 400.. karena client salah masukan key/field nya
        mockMvc.perform(
                post("/form/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("salah", "budhi")
                        .param("salah", "2023-10-10")
                        .param("salah", "indonesia")
        ).andExpectAll(
                status().isBadRequest()
        );

    }


}