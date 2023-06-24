package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
     *
     * Session Attribute
     * ● Seperti yang pernah dibahas di materi Java Servlet, bahwa di Java Servlet, kita bisa membuat Session
     * ● Kita tidak akan membahas bagaimana cara melakukan management session di sini, karena sudah
     *   dibahas lengkap di kelas Java Servlet
     * ● Spring WebMVC menyediakan cara mudah untuk mengakses data di Session menggunakan
     *   annotation @SessionAttribute
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/SessionAttribute.html
     *
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
         * endpoint: localhost:8080/auth/login/setcookie?username=budhi&password=rahsia
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

    @PostMapping(path = "/auth/login/session/current", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> loginSession(@RequestParam(name = "username") String username,
                                               @RequestParam(name = "password") String password,
                                               HttpServletResponse servletResponse,
                                               HttpServletRequest servletRequest){

        if ("budhi".equals(username) && "rahasia".equals(password)){
            // session
            HttpSession session = servletRequest.getSession(true); // HttpSession getSession(boolean var1) // Mengembalikan arus HttpSession yang terkait dengan permintaan ini atau, jika tidak ada sesi saat ini dan create true, mengembalikan new session.
            session.setAttribute("user", new User(username)); // void setAttribute(String var1, Object var2) // binding objek ke session ini, menggunakan nama yang ditentukan.

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
         * endpoint: localhost:8080/auth/login/session/current?username=budhi&password=rahsia
         *
         * response:
         * Headers = [Set-Cookie:"username=budhi; Path=/", Content-Type:"text/plain;charset=UTF-8", Content-Length:"2"]
         * Session Attrs = {user=User(username=budhi)}
         * Cookies = [[Cookie@764a3e5d name = 'username', value = 'budhi', comment = [null], domain = [null], maxAge = -1, path = '/', secure = false, version = 0, httpOnly = false]]
         *
         */

    }

    @GetMapping(path = "/user/session/current")
    @ResponseBody
    public ResponseEntity<String> getUserSession(@SessionAttribute(name = "user") User user){

        // @SessionAttribute // annotation yang handle untuk get session attrubute di server

        return ResponseEntity.ok("Hello " + user.getUsername());

        /**
         * endpoint: localhost:8080/user/session/current
         *
         * response:
         * Session Attrs = {user=User(username=budhi)}
         */
    }

}
