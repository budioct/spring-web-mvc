package com.tutorial.mvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Controller
public class UploadController {

    /**
     * Upload File
     * ● Upload File di Spring Web MVC bisa menggunakan cara seperti di Java Web Servlet, atau bisa
     *   menggunakan fitur di Spring Web MVC yang lebih mudah menggunakan annotation @RequestPart
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestPart.html
     * ● Untuk tipe data pada parameter nya, kita bisa gunakan MultipartFile
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html
     *
     * Upload File Properties
     * ● Spring memiliki pengaturan yang bisa kita atur untuk Upload file, misal kita ingin membatasi jumlah
     *   ukuran file misal nya, dan lain-lain
     * ● Semua pengaturan untuk upload file bisa kita tambahkan di application properties, dengan prefix spring.servlet.multipart
     * ● Kita bisa lihat daftarnya disini :
     * ● https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.web
     *  cari config properties: spring.servlet.multipart.*
     */

    @PostMapping(path = "/upload/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String upload(@RequestParam(name = "name") String name,
                         @RequestPart(name = "profile") MultipartFile profile) throws IOException {

        // import org.springframework.web.multipart.MultipartFile // object yang menenangani upload file spring web mvc

        //Path uploadLocation = Path.of("upload/" + UUID.randomUUID() + profile.getOriginalFilename()); // String getOriginalFilename() // return nama file asli di sistem file client.
        Path uploadLocation = Path.of("upload/" + profile.getOriginalFilename()); // String getOriginalFilename() // return nama file asli di sistem file client.

        // static Path write(Path path, byte[] bytes, OpenOption... options) // menyalin file dari resource
        // byte[] getBytes() throws IOException // membaca file dengan byte[]
        Files.write(uploadLocation, profile.getBytes());
        //Files.copy(profile.getInputStream(), uploadLocation);

        return "Success save profile " + name + " to " + uploadLocation;
    }

    /**
     * endpoint: localhost:8080/upload/profile?name=budhi&profile=namafile.jpg
     * header content-type: multipart/form-data
     */



}
