package com.tutorial.mvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // handle error Controller, seperti validation error, logic error, dan lain-lain. kita akan membuat response error sendiri
public class ErrorController {

    /**
     * Exception Handler
     * ● Saat terjadi error di Controller, seperti validation error, logic error, dan lain-lain
     * ● Secara default, Spring akan mengembalikan response error sesuai jenis errornya
     * ● Kadang, kita ingin membuat halaman atau response error sendiri
     * ● Hal ini bisa kita buat dengan menggunakan @ControllerAdvice
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html
     *
     * Controller Advice
     * ● Controller Advice adalah sebuah class yang dipanggil ketika sebuah jenis exception terjadi
     * ● Dengan begitu kita bisa memanipulasi response yang akan dikembalikan ke user menggunakan
     *   Controller Advice ini
     *
     * Exception Handler
     * ● Setelah membuat Controller Advice, untuk menangkap exception dan mengubah response nya,
     *   kita perlu membuat Method seperti di Controller
     * ● Namun kita tidak menggunakan annotation @RequestMapping, melainkan @ExceptionHandler
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html
     * ● Kita harus tentukan jenis exception apa yang akan ditangkap, dan jika butuh data exception nya,
     *   kita bisa tambahkan sebagai parameter di Method nya
     *
     */

    // ini adalah joinpoint dan pointcut Exception

    // error dari spring.. yang handle pesan Exception. dan berjalan di Runtime
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException exception){

        // @ExceptionHandler() // menanangkap error yang terjadi
        // MethodArgumentNotValidException // object yang di gunakan untuk menangkap error di spring

        return new ResponseEntity<>("Validator Error from : " + exception.getMessage(), HttpStatus.BAD_REQUEST); // ResponseEntity(@Nullable T body, HttpStatusCode status) // return value MethodArgumentNotValidException dan status code

        /**
         * testing endpoint: localhost:8080/person/api
         */
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(ConstraintViolationException exception){

        return new ResponseEntity<>("Validator Error from : " + exception.getMessage(), HttpStatus.BAD_REQUEST); // ResponseEntity(@Nullable T body, HttpStatusCode status) // return value ConstraintViolationException dan status code
    }

}
