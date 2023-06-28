package com.tutorial.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ModelAndViewController {

    /**
     * View
     * ● Sampai sekarang, kita sudah membahas tentang Controller di Spring WebMVC
     * ● Kita belum pernah membahas tentang View
     * ● Sebelumnya kita hanya mengembalikan koten web secara manual di Controller
     * ● Spring WebMVC sendiri tidak membuat fitur untuk View / Templating secara manual
     * ● Spring WebMVC mengintegrasikan banyak sekali teknologi untuk Templating yang digunakan sebagai bagian dari View nya
     *
     * View yang Didukung
     * ● Spring WebMVC mendukung banyak sekali library untuk View, misalnya
     * ● JSP (Java Server Page)
     * ● Apache Velocity : https://velocity.apache.org/
     * ● Apache Freemarker : https://freemarker.apache.org/
     * ● Mustache : https://mustache.github.io/
     * ● Thymeleaf : https://www.thymeleaf.org/
     * ● Di vidio ini, kita akan bahas integrasi dengan Mustache, namun untuk detail dari Mustache tidak dibahas di materi ini, karena sudah dibahas di materi kelas Java Mustache
     *
     * Mustache
     * ● Saat kita menambahkan dependecy Spring Boot Mustache, kita tidak perlu melakukan pengaturan
     *   secara manual lagi untuk membuat Mustache
     * ● Semua sudah diatur secara otomatis oleh Spring Boot
     * ● Template Mustache bisa kita simpan di folder /resources/templates/ dengan extension .mustache
     * ● Semua pengaturan bisa digunakan via application properties
     * ● https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.templating
     *
     * Model And View
     * ● Untuk menampilkan View, kita bisa mengembalika return object ModelAndView pada Controller Method
     * ● Dalam ModelAndView, kita bisa memasukkan data template yang dipilih untuk View, dan juga
     *   Model yang akan ditampilkan di View
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/ModelAndView.html
     *
     */

    @GetMapping(path = "/web/hello")
    public ModelAndView hello(@RequestParam(name = "name", required = false) String name){

        // untuk implementasi mustache kita menggunakan object ModelAndView dari java,
        // untuk handle menggunakan model yang mana dan view yang mana

        // ModelAndView(String viewName, @Nullable Map<String, ?> model)
        // param String viewName      // set dengan nama file nya saja tidak perlu exentionya juga yang ada di folder ./resources/templates/*.mustache
        // param Map<String, ?> model // yang mau kita kirim ke view nya. key adalah key {{variable mustache}} dan value adalah data dari model

        return new ModelAndView("hello", Map.of(
                "title", "Belajar View",
                "name", "Hello " + name
        ));

    }


}
