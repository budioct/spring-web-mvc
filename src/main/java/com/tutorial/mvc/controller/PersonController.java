package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.CreatePersonRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PersonController {

    /**
     * Model Attribute
     * ● Saat kita mengirim request berupa form dengan input yang banyak, kadang menyulitkan kita jika
     *   kita harus membuat semua parameter input dengan @RequestParam
     * ● Bayangkan jika ada 10 input, maka kita harus membuat 10 parameter @RequestParam
     * ● Spring memiliki fitur dimana kita bisa melakukan binding attribute yang dikirim dengan class Java
     *   Bean yang kita buat menggunakan annotation @ModelAttribute
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ModelAttribute.html
     *
     * Nested Model
     * ● Salah satu yang powerfull di Model Attribute adalah, kita bisa otomatis juga membuat object dari
     *   nested attribute di Model
     * ● Misal pada kasus Person sebelumnya, misal saja terdapat sebuah attribute Address yang
     *   merupakan Java Bean lainnya
     * ● Untuk mengisi data Address, kita bisa gunakan . (titik), misal address.street, address.city, dan
     *   seterusnya
     *
     * List
     * ● Selain nested attribute, kita juga bisa menggunakan List sebagai model attribute, cara mengirim
     *   parameter nya cukup mudah
     * ● Untuk list dengan tipe data primitive seperti String, Integer, dan sejenisnya, kita bisa gunakan
     * parameter :
     *   ○ namaParam[0]=data1
     *   ○ namaParam[1]=data2
     *   ○ Dan seterusnya
     * ● Untuk list dengan tipe data object lagi, kita bisa gunakan parameter :
     *   ○ namaParam[0].field1=data1
     *   ○ namaParam[0].field2=data2
     *   ○ namaParam[1].field1=data1
     *   ○ namaParam[1].field2=data2
     */

    @PostMapping(path = "/create/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String createPerson(@ModelAttribute CreatePersonRequest request){

        // @ModelAttribute annotation yang menghendle object/class bean. karna pasti akan melelahkan jika menggunkan @RequestParam

        return new StringBuilder().append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName())
                .append(" with email ").append(request.getEmail())
                .append(" and phone ").append(request.getPhone())
                .toString();

    }

    // nested object
    @PostMapping(path = "/create/personnedtedobject", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String createPersonNested(@ModelAttribute CreatePersonRequest request){

        // @ModelAttribute annotation yang menghendle object/class bean. karna pasti akan melelahkan jika menggunkan @RequestParam
        // @ModelAttribute juga power full dia bisa membaca nedted object yang di sematkan

        System.out.println(request);

        return new StringBuilder().append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName())
                .append(" with email ").append(request.getEmail())
                .append(" and phone ").append(request.getPhone())
                .append(" with address ")
                .append(request.getAddress().getStreet()).append(", ")
                .append(request.getAddress().getCity()).append(", ")
                .append(request.getAddress().getCountry()).append(", ")
                .append(request.getAddress().getPostalCode())
                .toString();

    }


}
