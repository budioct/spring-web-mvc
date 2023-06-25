package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.Partner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PartnerController {

    @GetMapping(path = "/partner/current")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String getPatner(Partner partner){

        // karena kita telah membuatkan entity Partner Resolver, jadi tidak perlu lagi menggunakan @ModelAttribure, @RequestBody, @RequestParam
        // error handle nya sudah di handle spring

        return  partner.getId() + " " + partner.getName();

        /**
         * endpoint: localhost:8080/partner/current
         */

    }

}
