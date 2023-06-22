package com.tutorial.mvc.service;

import com.tutorial.mvc.service.impl.HelloServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceImplTest {

    @Autowired
    HelloServiceImpl helloService;

    @Test
    void testHello(){

        Assertions.assertEquals("Hello Guest", helloService.hello(null));
        Assertions.assertEquals("Hello Budhi", helloService.hello("Budhi"));

    }


}
