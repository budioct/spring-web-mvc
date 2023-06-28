package com.tutorial.mvc.controller;

import com.tutorial.mvc.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormController {

    /**
     * Request Content Type
     * ● Saat kita membuat Controller Method, kita juga bisa membatasi jenis Content-Type yang dikirim oleh user
     * ● Contoh pada kasus melakukan submit data form, kita biasanya meminta Content-Type yang dikirim
     *   oleh user adalah application/x-www-form-urlencoded
     * ● Untuk membatasi tipe Content-Type, kita bisa tambahkan di @RequestMapping pada attribute consumes()
     *
     * Response Content Type
     *  ● Di @RequestMapping, selain consumes, terdapat juga attribute produces(), yang bisa kita gunakan
     *    untuk memberi tahu di HTTP Response, Content-Type dari response body yang dikembalikan
     *
     * Form Request
     * ● Seperti pernah kita bahas di materi Java Servlet, untuk mendapatkan data Form Request, kita bisa
     *   menggunakan cara yang sama dengan mendapatkan data di Query Parameter
     * ● Begitu pula di Spring Web MVC
     * ● Untuk mendapatkan data di Form Request, kita bisa menggunakan annotation @RequestParam
     * ● Secara otomatis Spring Web MVC juga akan mengambil data dari Form Request atau Query Parameter
     * ● Seperti yang pernah kita praktekan di materi Request Content Type
     *
     *
     */

    @Autowired
    HelloServiceImpl helloService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); // bentuk format date ketika di tampilkan 20231022

    @PostMapping(path = "/form/hello",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    // consumes() method yang handle Request  Content-Type dengan format form application/x-www-form-urlencoded
    // produces() method yang handle Response Content-Type dengan format sesuai ketentuan
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name){

        String data = helloService.hello(name);

        // return "Hello " + name; // response ke client
        // response html Content-Type ke client
        return """
                <html>
                <body>
                <h1>$name</h1>
                </body>
                </html>
                """.replace("$name", data);

        /**
         * endpoint: localhost:8080/form/hello?name=budhi
         */

    }

    @PostMapping(path = "/form/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createPerson(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "birthDate") Date birthDate,
            @RequestParam(name = "address") String address
    ){

        return  "Success create Person with name : " + name +
                ", birthDate: " + dateFormat.format(birthDate) +
                ", address : " + address;
        /**
         * endpoint: localhost:8080/form/person?name=budhi&birthDate=2023-10-10&address=indonesia
         */
    }



}
