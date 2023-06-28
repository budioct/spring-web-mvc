package com.tutorial.mvc.interceptor;

import com.tutorial.mvc.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    /**
     * Interceptor (WebFilter / Middelware) firewall url untuk aplikasi
     * ● Saat kita belajar Java Servlet, kita tahu ada fitur yang bernama WebFilter, yang tugasnya mirip
     *   sebagai middleware
     * ● Di Spring WebMVC, kita bisa menggunakan fitur bernama Interceptor, untuk melakukan hal yang sama
     * ● Cara melakukan registrasi Interceptor adalah dengan membuat class turunan dari
     *   HandlerInterceptor, lalu menambahkan menggunakan InterceptorRegistry di method addInterceptors() WebMvcConfigurer
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/HandlerInterceptor.html
     */

    // method yang telah dibuat sebaiknya di registrasikan ke MVC Config. class nya sudah kita buat di MyWebConfig.java yang implements WebMvcConfigurer
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // kita buat method ini bertugas sebagai middleware
        // kita akan ceak session attribute/key user. ketika datanya key user ada kita return true. jika tidak ada sessionya kita return false

        HttpSession session = request.getSession(true); // HttpSession getSession(boolean var1) // Mengembalikan arus HttpSession yang terkait dengan permintaan ini atau, jika tidak ada sesi saat ini dan create true, mengembalikan new session.
        User user = (User) session.getAttribute("user"); // Object getAttribute(String var1) // return object yang binding dengan nama yang ditentukan dalam session. atau null jika tidak ada objeck yang terikat dengan nama tersebut
        // ketika tidak ada session maka akan di redirect ke url login
        if (user == null){
            response.sendRedirect("/login"); // jika user null makan redirect status code 302 ke route /login
            return false; // jika tidak ada session kembalikan false
        }

        // jika ada session kembalikan true
        return true;

    }

}
