package com.tutorial.mvc.controller;

import com.tutorial.mvc.service.HelloService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Objects;

@Controller // @Controller untuk menandakan class ini adalah controller dan sudah di teregistrasi sebagai @Bean di spring boot
public class HelloController {

    /**
     * Controller
     * ● Untuk membuat Controller di Spring, kita bisa menggunakan annotation @Controller
     * ● Di annotation Controller sendiri, sebenarnya terdapat annotation Component, hal ini membuat
     *   class yang kita tambahkan annotation Controller, akan secara otomatis teregistrasi sebagai Bean
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html
     *
     * Request Mapping
     * ● Saat kita belajar menggunakan Servlet, untuk membuat Routing pada Servlet kita menggunakan annotation WebServlet
     * ● Di Spring WebMVC, untuk menambahkan Routing, kita bisa menggunakan annotation
     *   @RequestMapping pada method yang ingin kita jadikan sebagai Controller Handler nya
     * ● https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestMapping.html
     *
     * Menjalankan Web
     * ● Spring Boot secara default menambahkan Apache Tomcat sebagai Embedded Web Server
     * ● Hal ini menjadikan kita tidak perlu lagi untuk membuat aplikasi Spring Boot dalam bentuk War, dan
     *   tidak perlu melakukan deployment secara manual ke Apache Tomcat
     * ● Secara default, Spring Boot menggunakan port 8080 untuk menjalankan Apache Tomcat nya
     * ● Jika kita ingin mengubah port nya, kita bisa gunakan properties
     * ● server.port=NOMOR
     * ● Pada application.properties
     */

    @Autowired
    private HelloService helloService;

    @RequestMapping(path = "/hello") // @RequestMapping() yang handle routing http
    public void helloWorld(HttpServletResponse response) throws IOException {

        // HttpServletResponse // object web servlet java yang handle response ke client
        response.getWriter().println("Hello World"); // PrintWriter getWriter() throws IOException; // Mengembalikan PrintWriter objek yang dapat mengirim teks karakter ke klien.

        /**
         * endpoint: http://localhost:8080/hello
         */
    }

    /**
     * Servlet Request dan Response
     * ● Saat kita membuat Controller Handler dengan RequestMapping
     * ● Kita bisa menambahkan parameter HttpServletRequest atau HttpServletResponse jika memang
     *   butuh object tersebut
     * ● Tidak ada aturan posisi parameter, karena Spring WebMVC bisa mendeteksi secara otomatis tipe
     *   dan posisi parameter nya
     */

    @RequestMapping(path = "/hellobro")
    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name"); // String getParameter(String var1); // Mengembalikan nilai parameter permintaan sebagai String, atau nulljika parameter tidak ada.

        // jika parameter null makan akan di replace Guest
        if (Objects.isNull(name)){
            name = "Guest";
        }

        response.getWriter().println("Hello " + name);

        /**
         * endpoint: http://localhost:8080/hellobro?name=budhi
         */
    }

    /**
     * implementasi @Service dari class HelloServiceImpl
     */

    @RequestMapping(path = "/helloservice")
    public void helloService(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name"); // String getParameter(String var1); // Mengembalikan nilai parameter permintaan sebagai String, atau nulljika parameter tidak ada.
        String responseBody = helloService.hello(name); // implementasi service
        response.getWriter().println(responseBody);

        /**
         * endpoint: http://localhost:8080/helloservice?name=budhi
         */
    }

    /**
     * implementasi method http request. dengan annotation @RequestMapping pada method() contohnya dengan method HTTP GET --> RequestMethod.GET
     * jika method http tidak diterima makan Exception status code 405 Method Not Allowed
     */

    @RequestMapping(path = "/hellomethodget", method = RequestMethod.GET) // method() menentukan jenis HTTP method yang di perbolehkan untuk mengakses endpoint
    public void helloRequestMethodGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name"); // String getParameter(String var1); // Mengembalikan nilai parameter permintaan sebagai String, atau nulljika parameter tidak ada.
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);

        /**
         * endpoint: http://localhost:8080/hellomethodget?name=budhi
         */

    }

    @GetMapping(path = "/helloserviceshortcut") // sudah set HTTP METHOD GET untuk di consume
    public void helloServiceShortcut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // @GetMapping adalah shortcut dari @RequestMapping supaya sebih spesifik dan implisit, untuk menghandle enpoint

        String name = request.getParameter("name"); // String getParameter(String var1); // Mengembalikan nilai parameter permintaan sebagai String, atau nulljika parameter tidak ada.
        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);

        /**
         * endpoint: http://localhost:8080/helloserviceshortcut?name=budhi
         */
    }

    /**
     * implementasi @RequestParam dari spring.. sebagai penganti HttpServletRequest untuk handle menangkap request masuk dari client
     */

    @RequestMapping(path = "/helloservicerequestparam")
    public void helloService(@RequestParam(name = "name", required = false) String name,
                             HttpServletResponse response) throws IOException {
        // @RequestParam // menerima parameter request dari user. method name() sebagai set key nama parameter

        String responseBody = helloService.hello(name);
        response.getWriter().println(responseBody);

        /**
         * endpoint: http://localhost:8080/helloservicerequestparam?name=budhi
         */
    }

}
