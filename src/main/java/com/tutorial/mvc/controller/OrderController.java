package com.tutorial.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class OrderController {

    /**
     * Path Variable
     * ● Salah satu fitur Spring WebMVC yang berbeda dari Java Servlet adalah Path Variable
     * ● Path Variable adalah fitur dimana kita bisa membuat patterns pada URL Path, dan mengambil nilai
     *   yang terdapat di URL Path nya
     * ● Dengan fitur ini, kita bisa membuat URL Path yang dinamis, dan bisa mendapatkan nilai dinamis di
     *   URL Path nya secara otomatis
     * ● Untuk menggunakan fitur ini, kita perlu tambahkan variable path nya di URL Path nya, dan juga
     *   menambahkan parameter dengan annotation @PathVariable
     * ● Path Variable juga memiliki kemampuan otomatis konversi tipe data dengan Converter
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/PathVariable.html
     */

    @GetMapping(path = "/orders/{orderId}/products/{productId}")
    @ResponseBody
    public String order(
            @PathVariable("orderId") String orderId,
            @PathVariable("productId") String productId
    ){

        // @PathVariable kita bisa membuat endpoint secara dinamis seperti ini:
        // endpoint: /orders/1/products/2
        // @PathVariable("orderId") String orderId = parameter method
        // @PathVariable("productId") String productId = parameter method

        log.info("order_id: {}", orderId);
        log.info("product_id: {}", productId);

        return "Order: " + orderId + ", Product: " + productId; // Order: 1, Product: 2

        /**
         * endpoint: localhost:8080//orders/{orderId}/products/{productId}
         * endpoint: localhost:8080//orders/23/products/21
         */

    }



}
