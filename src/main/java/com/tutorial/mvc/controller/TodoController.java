package com.tutorial.mvc.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // annotation controller untuk Restful API. jadi kita perlu manual lagi menambahkan @ResponseBody di setiap method yang dibuat. semua otomatis di handle spring
public class TodoController {

    /**
     * Rest Controller
     * ● Sebelumnya kita sudah tahu untuk membuat Controller, kita menggunakan annotation Controller
     * ● Spring Web MVC menyediakan annotation khusus untuk membuat Controller khusus untuk
     *   RESTful API, yaitu annotation RestController
     * ● RestController ini sebenarnya gabungan antara @Controller dan @ResponseBody, yang artinya
     *   secara otomatis semua return Controller Method tersebut dianggap sebagai Response Body
     */

    private List<String> todos = new ArrayList<>(); // kita akan melakukan restful dengan menambahkan data ke List<T> []

    @PostMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> addTodo(@RequestParam(name = "todo") String todo){

        todos.add(todo);
        return todos; // akan tambahkan ke list []
    }

    @GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getTodos(){

        return todos; // akan mendapatkan data list []
    }

}
