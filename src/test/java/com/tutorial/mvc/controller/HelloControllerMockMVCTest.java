package com.tutorial.mvc.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerMockMVCTest {

    /**
     * MockMVC
     * ● Saat kita membuat Web menggunakan Spring WebMVC, Spring telah menyediakan fitur bernama MockMVC
     * ● Fitur ini digunakan untuk mempermudah kita melakukan unit test
     * ● Dengan menggunakan MockMVC, kita bisa mengetes semua Controller yang kita buat, tanpa
     *   harus menjalankan aplikasi Web nya, dan tidak perlu melakukan pengetesan secara manual
     *   menggunakan Browser atau HTTP Client
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html
     *
     * Static Imports
     * Ketika menggunakan MockMVC, kita butuh beberapa static utility method dari class-class berikut
     * ● MockMvcBuilders.*
     * ● MockMvcRequestBuilders.*
     * ● MockMvcResultMatchers.*
     * ● MockMvcResultHandlers.*
     *
     * kita akan melakukan mockmvc route endpoint http
     */

    @Autowired
    private MockMvc mockMvc; // untuk unit testing controller.. perlu di ingat MockMvc tidak akan dibuat menjadi @Bean kalau kita tidak tambahkan annotation @AutoConfigureMockMvc

    @Test
    void testHello() throws Exception {

        // ResultActions perform(RequestBuilder requestBuilder) throws Exception // Lakukan permintaan dan kembalikan jenis yang memungkinkan merantai tindakan lebih lanjut, seperti menyatakan ekspektasi, pada hasilnya.
        // static MockHttpServletRequestBuilder get(URI uri) // Buat MockHttpServletRequestBuilder untuk permintaan GET.
        // default ResultActions andExpectAll(ResultMatcher... matchers) throws Exception // Lakukan banyak harapan, dengan jaminan bahwa semua harapan akan ditegaskan bahkan jika satu atau lebih harapan gagal dengan pengecualian.
        // static StatusResultMatchers status() // Akses ke pernyataan status respons.
        // ResultMatcher isOk() // Tegaskan kode status respons adalah HttpStatus.OK(200).
        // static ContentResultMatchers content() // Akses ke pernyataan badan tanggapan.
        // ResultMatcher string(Matcher<? super String> matcher) // Menegaskan konten isi respons dengan Hamcrest Matcher.
        // static Matcher<java.lang.String> containsString(java.lang.String substring) // Membuat pencocokan yang cocok jika yang diperiksa String berisi yang ditentukan String di mana saja.

        // versi pipeline
        mockMvc.perform(
                get("/hello")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello World"))
        );

    }

    @Test
    void testGuest() throws Exception {

        // versi pipeline
        mockMvc.perform(
                get("/hellobro")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guest"))
        );

    }

    @Test
    void testHelloBudhi() throws Exception {

        // MockHttpServletRequestBuilder queryParam(String name, String... values) // Tambahkan ke string kueri dan tambahkan juga ke request parameters peta.

        // versi pipeline
        mockMvc.perform(
                get("/hellobro").queryParam("name", "budhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello budhi"))
        );

    }

}