package com.tutorial.mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorPageController implements ErrorController {

    /**
     * Error Page
     * ● Saat terjadi exception yang tidak tertangani oleh Exception Handler, secara default Spring
     *   WebMVC akan mengirim detail errornya ke path /error
     * ● Jika tidak ada Controller Method dengan Route /error, maka Spring akan menampilkan default
     *   Page untuk error tersebut
     *
     * Error Page Properties
     * ● Secara default, detail error tidak ditampilkan di error page, hal ini agar stacktrace tidak terexpose
     *   ketika terjadi error
     * ● Semua detail properties untuk error page bisa kita setting di application properties dengan prefix server.error
     * ● https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.server
     *
     * Membuat Error Detail Sendiri
     * ● Kita juga bisa membuat error detail page sendiri jika mau, namun kita harus mematikan fitur error
     *   detail page bawaan dari Spring Boot dengan menambahkan properties
     * ● server.server.error.whitelabel.enabled=false
     * ● Selanjutnya kita bisa membuat controller dengan route /error, namun kita wajib mengimplement
     *   interface ErrorController
     * ● https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/error/ErrorController.html
     * ● Dan ketika kita membuat error page, jika ingin mendapatkan detail errornya, kita bisa
     *   menggunakan HttpServletRequest, dengan mengambil attribute dengan key prefix
     *   RequestDispatcher.ERROR
     */

    @RequestMapping(path = "/error")
    public ResponseEntity<String> errorPage(HttpServletRequest request){

        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE); // menangkap status code route /error
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE); // menangkap pesan error route /error.. di message belum ada jadi message bisa saja null

        log.info("Status Code: {}", status.toString());
        log.info("Status Message: {}", message);

        String html= """
                <html>
                <body>
                <h1>$status - $message</h1>
                </body>
                </html>
                """.replace("$status", status.toString()).replace("$message", message);
        return ResponseEntity.status(status).body(html); // ResponseEntity return response dinamis dari spring

    }

}
