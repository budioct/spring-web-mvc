package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonApiController {

    /**
     * Json
     * ● Spring Web MVC terintegrasi dengan baik dengan library Jackson untuk menangani tipe data
     *   JSON, baik itu untuk consume dari Request Body atau produce ke Response Body
     * ● Saat kita menggunakan consume dengan tipe data JSON atau produce dengan tipe data JSON, kita
     *   tidak perlu secara manual melakukan konversi dari object ke JSON String, hal itu sudah otomatis di handle oleh Jackson
     *
     * Konfigurasi Jackson
     * ● Kita tidak perlu membuat Bean Jackson secara manual lagi, karena itu sudah di handle oleh Spring Boot
     * ● Jika kita butuh melakukan konfigurasi untuk Jackson, kita bisa menggunakan application properties
     * ● Semua daftar konfigurasinya bisa kita gunakan dengan prefix spring.jackson.
     * ● https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.json
     *
     * Validation
     * ● Spring WebMVC terintegrasi dengan baik dengan Bean Validation seperti yang sudah kita bahas di
     *   materi Spring Validation
     * ● Saat kita membuat parameter @ModelAttribute atau @RequestBody, jika object tersebut ingin di
     *   validasi secara otomatis menggunakan Bean Validation, kita bisa tambahkan annotation @Valid
     * ● Jika data tidal valid, secara otomatis Spring akan mengembalikan response 400 Bad Request
     * ● Khusus validasi di Controller, exception yang akan dibuat adalah
     *   MethodArgumentNotValidException bukan ConstraintViolationException nya Bean Validation
     */

    @PostMapping(
            path = "/api/person",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public CreatePersonRequest createPerson(@RequestBody @Valid CreatePersonRequest request){

        // @ModelAttribute annotation yang menghendle object/class bean (bisa menerima query param lebih dari 2 atau 10 parameter). karna pasti akan melelahkan jika menggunkan @RequestParam
        // @RequestBody // handle request client seperti format JSON, XML,  dll..
        // @Valid // terintegrasi dengan spring WEBMVC , saat membuat parameter @ModelAttribute atau @RequestBody, jika object tersebut ingin di
        // validasi secara otomatis menggunakan Bean Validation. Jika data tidal valid, secara otomatis Spring akan mengembalikan response 400 Bad Request (request di tolak)

        System.out.println(request);

        return request;
    }

}
