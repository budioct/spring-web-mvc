package com.tutorial.mvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    /**
     * Response Entity
     * ● Sekarang kita sudah tahu beberapa cara membuat HTTP Response, dari menggunakan
     *   HttpServletResponse dan @ResponseBody
     * ● Spring menyediakan cara yang sangat flexible untuk membuat HTTP Response menggunakan
     *   object ResponseEntity
     * ● Kita bisa return di Controller Method dengan object ResponseEntity
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
     *
     * Cookie
     * ● Cara membuat Cookie di Spring Web MVC bisa dilakukan dengan menggunakan
     *   HttpServletResponse seperti pada Java Servlet
     * ● Namun untuk membaca Cookie yang dikirim oleh Web Browser, kita bisa otomatis menggunakan annotation @CookieValue
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/CookieValue.html
     */

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "password") String password){

        if ("budhi".equals(username) && "rahasia".equals(password)){
            // return new ResponseEntity<>("OK", HttpStatus.OK); // versi standart
            return ResponseEntity.status(HttpStatus.OK).body("OK"); // versi builder
        } else {
            // return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED); // versi standart
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("KO"); // versi builder
        }

        /**
         * endpoint: localhost:8080/auth/login?username=budhi&password=rahsia
         */

    }

    @PostMapping(path = "/auth/login/setcookie", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> loginCookie(@RequestParam(name = "username") String username,
                                        @RequestParam(name = "password") String password,
                                        HttpServletResponse servletResponse){

        if ("budhi".equals(username) && "rahasia".equals(password)){

            // cookie
            Cookie cookie = new Cookie("username", username); // Cookie(String name, String value) // set bind username yang berhasil login
            cookie.setPath("/"); // path/route // Menentukan jalur untuk cookie tempat klien harus mengembalikan cookie.
            servletResponse.addCookie(cookie); // Menambahkan cookie yang ditentukan ke respons.

            // return new ResponseEntity<>("OK", HttpStatus.OK); // versi standart
            return ResponseEntity.status(HttpStatus.OK).body("OK"); // versi builder
        } else {
            // return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED); // versi standart
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("KO"); // versi builder
        }

        /**
         * endpoint: localhost:8080/auth/login?username=budhi&password=rahsia
         *
         * response:
         * Set-Cookie= username=budhi; Path=/
         */

    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getUserCookie(@CookieValue("username") String username){

        // @CookieValue // membaca Cookie yang dikirim oleh Web Browser

        return ResponseEntity.ok("Hello " + username);

        /**
         * endpoint: localhost:8080/auth/user
         *
         * response:
         * Set-Cookie= username=budhi; Path=/
         */
    }

}
