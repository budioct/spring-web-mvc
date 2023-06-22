package com.tutorial.mvc.controller;

import com.tutorial.mvc.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerMockBean {

    /**
     * Mock Bean
     * ● Saat kita belajar di kelas Java Unit Test, kita sudah belajar tentang melakukan mock menggunakan Mockito
     * ● Saat kita menggunakan Spring, kita juga melakukan hal tersebut
     * ● Selain itu, Spring juga bisa secara otomatis meregistrasikan Mock object tersebut sebagai bean,
     *   sehingga class yang membutuhkan bean tersebut, secara otomatis bisa mendapatkan Mock object yang kita buat
     * ● Untuk membuat Mock Bean, kita cukup gunakan annotation @MockBean
     * ● https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/mock/mockito/MockBean.html
     */

    @Autowired
    private MockMvc mockMvc; // untuk unit testing controller.. perlu di ingat MockMvc tidak akan dibuat menjadi @Bean kalau kita tidak tambahkan annotation @AutoConfigureMockMvc

    @MockBean
    private HelloService helloService; // menambahn behavior di class service

    @BeforeEach
    void setUp() {

        // versi pipeline mockito
        // mockito merubah behavior method hello() parameter apapun itu, makan hasil akhirnya akan return Hello Guys

        // static <T> OngoingStubbing<T> when(T methodCall) // menyimpan informasi tentang pemanggilan metode tiruan
        // static String anyString() // menerima setiap parameter String
        // OngoingStubbing<T> thenReturn(T var1) // mematikan yang sedang berlangsung yang dikembalikan. (merubah behavior)
        Mockito
                .when(helloService.hello(Mockito.anyString()))
                .thenReturn("Hello Guys");

    }

    @Test
    void testHelloGuest() throws Exception {


        mockMvc.perform(
                get("/helloservice")
                        .queryParam("name", "budhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Guys"))
        ); // walaupun kita sudah set param name dengan value budhi tidak akan berperngaruh. karena behaviornya sudah si rubah oleh mockito


    }

}
