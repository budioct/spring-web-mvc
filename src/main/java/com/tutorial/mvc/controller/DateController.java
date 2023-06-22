package com.tutorial.mvc.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateController {

    /**
     * Response Body
     * ● Secara default, kita harus menuliskan response dari Controller Method ke HttpServletResponse
     * ● Namun hal ini kadang menyulitkan jika misal kita hanya ingin mengembalikan data berupa String
     * ● Spring memiliki annotation @ResponseBody, yang bisa secara otomatis menjadikan data yang
     *   dikembalikan dari Controller Method menjadi data yang ditulis ke HttpServletResponse
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html
     *
     * Response Body
     * ● Secara default, kita harus menuliskan response dari Controller Method ke HttpServletResponse
     * ● Namun hal ini kadang menyulitkan jika misal kita hanya ingin mengembalikan data berupa String
     * ● Spring memiliki annotation @ResponseBody, yang bisa secara otomatis menjadikan data yang
     *   dikembalikan dari Controller Method menjadi data yang ditulis ke HttpServletResponse
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ResponseBody.html
     *
     */

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); // bentuk format date ketika di tampilkan 20231022

    @GetMapping(path = "/date")
    public void getDate(@RequestParam(name = "date") Date date,
                        HttpServletResponse response) throws IOException {

        // spring nantinya akan mencari converter date secara otomatis yang telah dibuat

        response.getWriter().println("Date : " + dateFormat.format(date)); // final String format(Date date) // konversi dari Date ke String

    }

    @GetMapping(path = "/datewithresponsebody")
    @ResponseBody
    public String getDateWithResponseBody(@RequestParam(name = "date") Date date) throws IOException {

        // @ResponseBody kita tidak perlu lagi HttpServletResponse dan menuliskan feedback manual untuk client seperti: response.getWriter().println()
        // spring nantinya akan mencari converter date secara otomatis yang telah dibuat

        return "Date : " + dateFormat.format(date); // final String format(Date date) // konversi dari Date ke String

    }


}