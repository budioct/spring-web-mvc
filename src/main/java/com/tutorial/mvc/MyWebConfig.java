package com.tutorial.mvc;

import com.tutorial.mvc.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // annotation teregister sebagai konfigurasi/pengaturan spring
public class MyWebConfig implements WebMvcConfigurer {

    /**
     * MVC Config
     * ● Saat membuat aplikasi web menggunakan Spring Web MVC, kita bisa menambahkan pengaturan
     *   untuk Spring Web MVC
     * ● Caranya kita perlu membuat sebuah Bean configuration turunan dari WebMvcConfigurer
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html
     * ● Ada banyak sekali method yang bisa kita override untuk menambah konfigurasi yang ada di Spring Web MVC
     *
     * Ant Path Matcher
     * ● Spring kebanyakan menggunakan Ant Path Matcher untuk pattern penulisan path
     * ● Format Ini diambil dari sebuah library bernama Apache Ant
     * ● Untuk lebih detail, kita bisa cek di class AntPathMatcher
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html
     */

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // method ini bertugas pencegat siklus hidup Spring MVC untuk pra- dan pasca-pemrosesan pemanggilan metode control dan request penangan sumber daya.
        // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/AntPathMatcher.html // jika ingin melihat pattern url
        // endpoint: /user/*
        // endpoint: /user/**

        registry.addInterceptor(sessionInterceptor).addPathPatterns("/user/**"); // InterceptorRegistration addPathPatterns(String... patterns) // kita bisa set patter url

    }
}
