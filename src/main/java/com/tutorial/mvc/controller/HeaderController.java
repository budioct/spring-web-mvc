package com.tutorial.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

    /**
     * Request Header
     * ● Untuk mendapatkan Http Request Header, seperti yang sudah kita pelajari di materi Java Servlet,
     *   kita bisa mendapatkannya melalui HttpServletRequest
     * ● Namun Spring WebMVC memiliki cara lebih mudah dengan menggunakan annotation @RequestHeader
     * ● Caranya kita bisa tambahkan di parameter di Controller Method
     * ● Kita juga bisa menentukan apakah wajib atau tidak, dan juga default value nya
     * ● Selain itu, fitur Converter juga bisa digunakan untuk Request Header
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestHeader.html
     */

    @GetMapping(path = "/header/token")
    @ResponseBody
    public String header(@RequestHeader(name = "X-TOKEN") String token){

        // @RequestHeader // untuk handle request header kepada user

        if (token.equals("Budhi")){
            return "header OK";
        } else {
            return "KO";
        }
    }


}
