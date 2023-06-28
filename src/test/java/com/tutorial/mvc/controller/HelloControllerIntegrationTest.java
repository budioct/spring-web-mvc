package com.tutorial.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;



@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // webEnvironment() membuat lingkungan test dengan set port yang acak. supaya tidak bentrok dengan port yang lain
public class HelloControllerIntegrationTest {

    /**
     * Integration Test
     * ● Saat kita menggunakan MockMVC, Spring tidak akan menjalankan aplikasi web kita
     * ● Spring hanya menyediakan mock request da mock response
     * ● Test yang mensimulasikan saat aplikasi berjalan adalah menggunakan mode Integration Test
     * ● Integration Test artinya adalah menjalankan aplikasi web secara lengkap, bersama dengan web
     *   server nya (Apache Tomcat)
     * ● Secara otomatis kita bisa menjalankan aplikasi web ketika test berjalan, dan menghentikannya
     *   ketika test selesai
     *
     * Test Rest Template
     * ● Berbeda ketika kita menggunakan MockMVC, saat menggunakan mode Integration Test, karena
     *   tidak menggunakan mock lagi, maka untuk mengetest aplikasi, kita harus benar-benar mengirim request ke aplikasi web
     * ● Spring memiliki HTTP Client bernama RestTemplate, yang akan kita bahas di materi khusus
     * ● Dan spesial untuk integration test, kita bisa menggunakan object TestRestTemplate
     *
     * Random Port
     * ● Secara default, saat menjalankan Integration Test, Spring akan menjalankan aplikasi sesuai dengan
     *   port di properties server.port
     * ● Namun kadang-kadang, portnya bentrok dengan port lain, oleh karena itu direkomendasikan
     *   menggunakan random port
     * ● Random port artinya Spring akan mencoba mendeteksi port yang belum digunakan, nanti secara
     *   otomatis akan menggunakan port tersebut
     * ● Untuk mendapatkan nilai port nya, kita bisa menggunakan inject @Value(“${local.server.port}”)
     *   atau lebih mudah menggunakan @LocalServerPort
     *
     */

    @LocalServerPort // Untuk mendapatkan nilai port nya. yang sudah di set webEnvironment() port random
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate; // Integration Test // untuk menjalankan enpoint secara langsung dengan tomcat

    @Test
    void testHello(){

        // <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... urlVariables) // Ambil entitas dengan melakukan GET pada URL yang ditentukan.
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/hello", String.class);
        String body = response.getBody(); // T getBody() // Buat yang baru Http Entity dengan body yang diberikan dan tanpa tajuk.

        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello World", body.trim()); // String trim() // memotong prefix dan suffix value variable

        log.info("Port: {}", port);
        log.info("Response: {}", body.trim());

    }

    @Test
    void testHelloGuest(){

        // <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... urlVariables) // Ambil entitas dengan melakukan GET pada URL yang ditentukan.
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/hellobro", String.class);
        String body = response.getBody(); // T getBody() // Buat yang baru Http Entity dengan badan yang diberikan dan tanpa tajuk.

        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello Guest", body.trim());

        log.info("Port: {}", port);
        log.info("Response: {}", body.trim());

    }

    @Test
    void testHelloBudhi(){

        // <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... urlVariables) // Ambil entitas dengan melakukan GET pada URL yang ditentukan.
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/hellobro?name=Budhi", String.class);
        String body = response.getBody(); // T getBody() // Buat yang baru Http Entity dengan badan yang diberikan dan tanpa tajuk.

        Assertions.assertNotNull(body);
        Assertions.assertEquals("Hello Budhi", body.trim());

        log.info("Port: {}", port);
        log.info("Response: {}", body.trim());

    }


}
