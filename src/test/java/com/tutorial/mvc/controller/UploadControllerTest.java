package com.tutorial.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UploadControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testUploadFile() throws Exception {

        // static MockMultipartHttpServletRequestBuilder multipart(String urlTemplate, Object... uriVariables) // Buat MockMultipartHttpServletRequestBuilder permintaan multipart, menggunakan POST sebagai metode HTTP.
        // MockMultipartHttpServletRequestBuilder file(MockMultipartFile file) // Tambahkan yang diberikan MockMultipartFile.
        // MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, InputStream contentStream) throws IOException // Buat MockMultipartFile baru dengan konten yang diberikan.
        // InputStream getResourceAsStream(String name) // Menemukan file pada directory resources dengan nama tertentu.
        // MockHttpServletRequestBuilder param(String name, String... values) // Tambahkan parameter permintaan ke MockHttpServletRequest.getParameterMap()
        // MockHttpServletRequestBuilder contentType(MediaType contentType) // Set the 'Content-Type' header of the request.

        mockMvc.perform(
                multipart("/upload/profile")
                        .file(new MockMultipartFile("profile", "luffy.jpeg",
                                "images/jpeg", getClass().getResourceAsStream("/images/luffy.jpeg")))
                        .param("name", "budhi")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success save profile budhi to upload/luffy.jpeg"))
        );

    }
}