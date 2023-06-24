package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.User;
import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testLoginOK() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("username", "budhi")
                        .queryParam("password", "rahasia")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("OK"))
        );
    }

    @Test
    void testLoginKO() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("username", "budhi")
                        .queryParam("password", "salah")
        ).andExpectAll(
                status().isUnauthorized(),
                content().string(Matchers.containsString("KO"))
        );
    }

    @Test
    void testLoginSuccessCookie() throws Exception {

        mockMvc.perform(
                post("/auth/login/setcookie")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("username", "budhi")
                        .queryParam("password", "rahasia")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("OK")),
                cookie().value("username", Matchers.is("budhi"))
        );
    }

    @Test
    void testGetUserCookie() throws Exception {

        mockMvc.perform(
                get("/auth/user")
                        .cookie(new Cookie("username", "budhi"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello budhi"))
        );
    }

    @Test
    void testLoginSuccessSession() throws Exception {

        mockMvc.perform(
                post("/auth/login/session/current")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .queryParam("username", "budhi")
                        .queryParam("password", "rahasia")
                        .sessionAttr("user", new User("budhi"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("OK")),
                cookie().value("username", Matchers.is("budhi"))
                // content().string(Matchers.containsString("Hello budhi"))
        );
    }

    @Test
    void testGetUserSession() throws Exception {

        mockMvc.perform(
                get("/user/session/current")
                        .sessionAttr("user", new User("budhi"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello budhi"))
        );
    }

}