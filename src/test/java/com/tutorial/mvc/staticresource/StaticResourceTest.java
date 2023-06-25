package com.tutorial.mvc.staticresource;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StaticResourceTest {

    /**
     * Static Resource
     * ● Saat kita membuat website, kita sering sekali membuat konten static, misal html, css, javascript,
     *   image, video, dan sejenisnya
     * ● Jika kita handle semua dengan membuat Controller atau Servlet, maka akan menyulitkan
     * ● Untungnya Spring WebMVC, memiliki fitur untuk menangani Static Resource ini
     * ● Kita bisa menambahkan semua resource static di folder static di directory resources
     * note:
     * ● Ketika kita mengakses Path di Spring Web MVC, pertama Spring akan mencoba mencari Controller
     *   yang memiliki Request Mapping tersebut, jika tidak ada, secara otomatis akan mencoba mengakses
     *   Static Resource, jika ternyata masih tidak ada, maka barus akan mengembalikan 404 Not Found
     */

    @Autowired
    MockMvc mockMvc;

    @Test
    void getStaticResource() throws Exception {

        /**
         * perlu di ingat
         * Ketika kita mengakses Path di Spring Web MVC,
         * - pertama Spring akan mencoba mencari Controller yang memiliki Request Mapping tersebut, jika tidak ada,
         * - kedua secara otomatis akan mencoba mengakses Static Resource, jika ternyata masih tidak ada
         * - ketiga maka barus akan mengembalikan 404 Not Found
         */

        mockMvc.perform(
                get("/index.html")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Static"))
        );

    }


}
