package com.tutorial.mvc.service;

public interface HelloService {

    // interface sebagai kontrak class impl logic

    /**
     * Interface
     * ● Salah satu best practice di Spring adalah, saat kita membuat Service Layer, kita akan buat dalam
     *   bentuk Interface
     * ● Lalu kita akan buat class implementasi yang diregistrasikan sebagai Spring Bean
     * ● Sedangkan class yang membutuhkan Service Layer tersebut, akan menggunakan Interface nya,
     *   bukan class implementasinya
     * ● Salah satu keuntungan mengekspos Interface dibanding Class adalah, kita bisa mengubah atau
     *   mengganti isi dari class implementasi, tanpa berdampak pada class lain yang menggunakan interface nya
     */

    String hello(String name);

}
