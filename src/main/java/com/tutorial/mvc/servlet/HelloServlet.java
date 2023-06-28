package com.tutorial.mvc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/hello")
public class HelloServlet extends HttpServlet {

    /**
     * Servlet Integration
     * ● Saat kita membuat WebServlet atau WebFilter, secara default Spring WebMVC tidak akan
     *   meregistrasikannya ke Embedded Apache Tomcat
     * ● Jika kita ingin membuat WebServlet dan WebFilter, dan ingin Spring otomatis meregistrasikan nya
     *   ke Embedded Apache Tomcat, maka kita perlu menggunakan annotation ServletComponentScan
     * ● https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/ServletComponentScan.html
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().println("Hello from Servlet");
    }
}