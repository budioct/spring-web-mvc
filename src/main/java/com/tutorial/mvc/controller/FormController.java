package com.tutorial.mvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormController {

    /**
     * Request Content Type
     * ● Saat kita membuat Controller Method, kita juga bisa membatasi jenis Content-Type yang dikirim oleh user
     * ● Contoh pada kasus melakukan submit data form, kita biasanya meminta Content-Type yang dikirim
     *   oleh user adalah application/x-www-form-urlencoded
     * ● Untuk membatasi tipe Content-Type, kita bisa tambahkan di @RequestMapping pada attribute consume()
     *
     * Response Content Type
     *  ● Di @RequestMapping, selain consume, terdapat juga attribute produce(), yang bisa kita gunakan
     *    untuk memberi tahu di HTTP Response, Content-Type dari response body yang dikembalikan
     *
     */

    @PostMapping(path = "/form/hello",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    // consumes() method yang handle Request  Content-Type dengan format form application/x-www-form-urlencoded
    // produces() method yang handle Response Content-Type dengan format sesuai ketentuan
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name){

        // return "Hello " + name; // response ke client
        // response html Content-Type ke client
        return """
                <html>
                <body>
                <h1>Hello $name</h1>
                </body>
                </html>
                """.replace("$name", name);
    }

}
