package com.tutorial.mvc.controller;

import com.tutorial.mvc.entity.CreatePersonRequest;
import com.tutorial.mvc.entity.CreateSocialMediaRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

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
     *
     * Validation
     * ● Spring WebMVC terintegrasi dengan baik dengan Bean Validation seperti yang sudah kita bahas di
     *   materi Spring Validation
     * ● Saat kita membuat parameter @ModelAttribute atau @RequestBody, jika object tersebut ingin di
     *   validasi secara otomatis menggunakan Bean Validation, kita bisa tambahkan annotation @Valid
     * ● Jika data tidal valid, secara otomatis Spring akan mengembalikan response 400 Bad Request
     * ● Khusus validasi di Controller, exception yang akan dibuat adalah
     *   MethodArgumentNotValidException bukan ConstraintViolationException nya Bean Validation
     *
     * Binding Result
     * ● Secara default, jika terjadi error di @ModelAttribute atau @RequestBody, maka akan throw
     *   exception MethodArgumentNotValidExceptio
     * ● Kadang kita tidak ingin hal itu terjadi, misal kita ingin tetap masuk ke Controller Method, karena di
     *   dalam nya kita ingin menampilkan halaman errornya misalnya
     * ● Pada kasus seperti itu, kita bisa tambahkan parameter BindingResult di sebelah parameter nya,
     *   secara otomatis detail error akan dimasukkan ke object BindingResult
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/BindingResult.html
     */

    @PostMapping(path = "/create/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String createPerson(@ModelAttribute @Valid CreatePersonRequest request){

        // @ModelAttribute annotation yang menghendle object/class bean. karna pasti akan melelahkan jika menggunkan @RequestParam
        // @Valid // terintegrasi dengan spring WEBMVC , saat membuat parameter @ModelAttribute atau @RequestBody, jika object tersebut ingin di
        // validasi secara otomatis menggunakan Bean Validation. Jika data tidal valid, secara otomatis Spring akan mengembalikan response 400 Bad Request (request di tolak)

        return new StringBuilder().append("Success create person ")
                .append(request.getFirstName()).append(" ")
                .append(request.getMiddleName()).append(" ")
                .append(request.getLastName())
                .append(" with email ").append(request.getEmail())
                .append(" and phone ").append(request.getPhone())
                .toString();

        /**
         * method post http
         * endpoint: localhost:8080/create/person?objectCreatePersonRequest=valueAttribute
         */

    }

    // nested object
    @PostMapping(path = "/create/personnedtedobject", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String createPersonNested(@ModelAttribute @Valid CreatePersonRequest request){

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

        /**
         * method post http
         * endpoint: localhost:8080/create/personnedtedobject?objectCreatePersonRequest=valueAttribute&objcetCreateAddressRequest=valueAttribute&objectCreateSocialMediaRequest=valueAttribute
         */

    }

    // binding result
    @PostMapping(path = "/create/person/bindingresult", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> createPersonBindingResult(@ModelAttribute @Valid CreatePersonRequest request,
                                               BindingResult bindingResult){

        // List<ObjectError> getAllErrors() // Dapatkan semua error, baik global field lapangan.
        // List<FieldError> getFieldErrors() // Dapakan error, dengan field entity
        List<FieldError> errors = bindingResult.getFieldErrors();
        if (!errors.isEmpty()){

            errors.forEach(fieldError -> {
                // System.out.println(objectError.getDefaultMessage());
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage()); // ketika filed yang di si set @NotBlank akan  akan di tampilkan field error
            });
           // return ResponseEntity.badRequest().body("you send invalid data");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you send invalid data"); // getDefaultMessage() diganti dengan pesan error ini
        }

        System.out.println(request);

        String response =  new StringBuilder().append("Success create person ")
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

        return ResponseEntity.ok(response);
        /**
         * method post http
         * endpoint: localhost:8080/create/person/bindingresult?objectCreatePersonRequest=valueAttribute
         */

    }


}
