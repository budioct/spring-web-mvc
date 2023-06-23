package com.tutorial.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CodeController {

    /**
     * Response Status
     * ● Saat kita membuat HTTP Response, kadang kita ingin mengubah Response Status Code
     * ● Secara default, response sukses adalah 200, kadang mungkin kita ingin ubah secara manual
     * ● Jika kita ingin ubah secara dinamis, kita bisa gunakan HttpServletResponse
     * ● Atau jika kita ingin hardcode response status nya, kita bisa gunakan annotation @ResponseStatus
     *   di Controller Method nya
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseStatus.html
     */

    @DeleteMapping(path = "/products/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED) // code: 202 Accepted
    public void delete(@PathVariable("id") Integer id){

        // @ResponseStatus(HttpStatus.enum) // merubah statuc code secara hardcode
        // delete to database

    }


}
