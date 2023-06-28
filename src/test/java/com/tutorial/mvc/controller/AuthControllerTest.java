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

        // CookieResultMatchers cookie() // ber ekpetasi bahwah setelah berhasil login kita akan di berikan value cookie yang sudah di set dengan key
        // ResultMatcher value(String name, Matcher<? super String> matcher) // kita akan ambil key cookie untuk di uji apakah value nya sesuai dengan username login
        // <T> org.hamcrest.Matcher<T> is(T value) // cocokan key:value cookie yang di dapat oleh username login

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

        // kita akan menangkap cookie yang di kirim oleh web browser dengan annotation @CookieValue dengan key username yang sudah di set dari server
        // MockHttpServletRequestBuilder cookie(Cookie... cookies) // ber ekpetasi bawah cookie key yang sudah di set username, apakah valuenya budhi dari client yang berhasil login


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

        // MockHttpServletRequestBuilder sessionAttr(String name, Object value) // ekpetasi dengan session key name di set "user" value yang kita ambil dari server kolom/field username

        mockMvc.perform(
                get("/user/session/current")
                        .sessionAttr("user", new User("budhi"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello budhi"))
        );
    }

    // Test url Middleware
    @Test
    void testMiddlewaregetSessionUser() throws Exception {

        // user yang tidak login atau tidak mendapatkan session atau session expire akan di redirect ke /login url root awal
        // karena kita tidak setSessionAttrubute nya maka kita tidak bisa mendapat feedback dari HttpServletResponse unutk mendapatkan session yang telah di set dengan key user dan reference object param dari User

        mockMvc.perform(
                get("/user/session/current")
                        //.sessionAttr("user", new User("budhi"))
        ).andExpectAll(
                status().is3xxRedirection()
        );

    }


}