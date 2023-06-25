package com.tutorial.mvc.resolver;

import com.tutorial.mvc.entity.Partner;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PatnerArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * Argument Resolver
     * ● Saat kita menambahkan sebuah parameter di Controller Method, maka Spring Web MVC akan
     *   mencoba mencari dari mana data tersebut berasal
     * ● Oleh karena itu kita perlu tambahkan penanda seperti ModelAttribute, RequestBody, RequestParam, dan lain-lain
     * ● Kita juga bisa membuat sebuah ArgumentResolver, yaitu class yang digunakan untuk mengisi
     *   object argument yang kita inginkan secara otomatis
     * ● Spring akan otomatis memanggil ArgumentResolver tersebut, ketika terdapat parameter dengan
     *   tipe data yang sudah kita tentukan
     *
     * Handler Method Argument Resolver
     *  ● Untuk membuat Argument Resolver, kita harus membuat class turunan
     *    HandlerMethodArgumentResolver
     *  ● Setelah itu kita harus registrasikan ke WebMvcConfigurer melalui methodaddArgumentResolvers()
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/method/support/HandlerMethodArgumentResolver.html
     *
     * Kekurangan Menggunakan Argument Resolver
     * ● Argument Resolver hanyalah untuk mengisi data yang terdapat di parameter Controller Method
     * ● Kita tidak bisa memodifikasi Http Response seperti di Interceptor
     * ● Oleh karena itu, jika butuh melakukan modifikasi, kita bisa kombinasikan Interceptor dan Argument Resolver
     * ● Misal dengan mengirim data dari Interceptor melalui Request Attribute, dan diterima di Argument Resolver
     *
     * // ketika kita sudah membuat resolver, daftarkan ke Web MVC Configurer
     * // dengan method addArgumentResolver()
     */

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // Class<?> getParameterType() // return jenis parameter metode/konstruktor. // sesuai dengan class reflection Partner
        return parameter.getParameterType().equals(Partner.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest(); // return objek permintaan asli yang mendasarinya.
        String apiKey = servletRequest.getHeader("X-API-KEY"); // get nilai header yang ditentukan sebagai String.
        if (apiKey != null){
            return new Partner(apiKey, "Sample Partner"); // jika key header ada instance
        }

        throw new RuntimeException("Unauthorized Exception"); // jika key header tidak ada error runtime

    }
}
