package com.tutorial.mvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller // @Controller untuk menandakan class ini adalah controller dan sudah di teregistrasi sebagai @Bean di spring boot
public class HelloController {

    /**
     * Controller
     * ● Untuk membuat Controller di Spring, kita bisa menggunakan annotation @Controller
     * ● Di annotation Controller sendiri, sebenarnya terdapat annotation Component, hal ini membuat
     *   class yang kita tambahkan annotation Controller, akan secara otomatis teregistrasi sebagai Bean
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html
     *
     * Request Mapping
     * ● Saat kita belajar menggunakan Servlet, untuk membuat Routing pada Servlet kita menggunakan annotation WebServlet
     * ● Di Spring WebMVC, untuk menambahkan Routing, kita bisa menggunakan annotation
     *   @RequestMapping pada method yang ingin kita jadikan sebagai Controller Handler nya
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
     *
     * Menjalankan Web
     * ● Spring Boot secara default menambahkan Apache Tomcat sebagai Embedded Web Server
     * ● Hal ini menjadikan kita tidak perlu lagi untuk membuat aplikasi Spring Boot dalam bentuk War, dan
     *   tidak perlu melakukan deployment secara manual ke Apache Tomcat
     * ● Secara default, Spring Boot menggunakan port 8080 untuk menjalankan Apache Tomcat nya
     * ● Jika kita ingin mengubah port nya, kita bisa gunakan properties
     * ● server.port=NOMOR
     * ● Pada application.properties
     */

    @RequestMapping(path = "/hello") // @RequestMapping() yang handle routing http
    public void helloWorld(HttpServletResponse response) throws IOException {
        // HttpServletResponse // object web servlet java yang handle response ke client
        response.getWriter().println("Hello world"); // PrintWriter getWriter() throws IOException; // Mengembalikan PrintWriter objek yang dapat mengirim teks karakter ke klien.
    }

}
