package com.tutorial.mvc.service.impl;

import com.tutorial.mvc.service.HelloService;
import org.springframework.stereotype.Service;

@Service // @Service untuk menandakan class ini adalah service dan sudah di teregistrasi sebagai @Bean di spring boot
public class HelloServiceImpl implements HelloService {

    /**
     * Service Layer
     * ● Di awal kita belajar tentang MVC (Model View Controller)
     * ● Di bahasa pemrograman atau framework lain, biasanya orang menambahkan kode yang
     * berhubungan dengan bisnis logic di Controller Layer, namun berbeda dengan programmer Java
     * ● Untuk programmer Java, sebenarnya kebiasaan atau best practice nya akan membuat layer khusus
     * untuk kode bisnis logic, bernama Service Layer
     * ● Service Layer di Spring memiliki annotation khusus, yaitu @Service
     * ● Saat kita menambahkan @Service, secara otomatis juga class tersebut akan di registrasikan sebagai bean
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html
     */

    @Override
    public String hello(String name) {
        if (name == null) {
            return "Hello Guest";
        } else {
            return "Hello " + name;
        }
    }

}
