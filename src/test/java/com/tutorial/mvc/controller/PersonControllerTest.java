package com.tutorial.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testCreatePerson() throws Exception {

        // testing dengan parameter object. yang di handle dengan annotation @ModelAttribute

        mockMvc.perform(
                post("/create/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName", "budhi")
                        .param("middleName", "oct")
                        .param("lastName", "octaviansyah")
                        .param("email", "budhi@test.com")
                        .param("phone", "08999912222")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person " +
                        "budhi oct octaviansyah with email budhi@test.com and phone 08999912222"))
        );

    }

    @Test
    void testCreatePersonBadRequest() throws Exception {

        // testing dengan parameter object. yang di handle dengan annotation @ModelAttribute
        // @Valid badrequest dengan attribute yang null

        mockMvc.perform(
                post("/create/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                        .param("firstName", "budhi")
                        .param("middleName", "oct")
                        .param("lastName", "octaviansyah")
//                        .param("email", "budhi@test.com")
//                        .param("phone", "08999912222")
        ).andExpectAll(
                status().isBadRequest()
        );

    }

    @Test
    void testCreatePersonNestedObject() throws Exception {

        // testing dengan parameter object. yang di handle dengan annotation @ModelAttribute
        // @ModelAttribute annotation yang menghendle object/class bean. karna pasti akan melelahkan jika menggunkan @RequestParam
        // @ModelAttribute juga power full dia bisa membaca nedted object yang di sematkan

        mockMvc.perform(
                post("/create/personnedtedobject")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName", "budhi")
                        .param("middleName", "oct")
                        .param("lastName", "octaviansyah")
                        .param("email", "budhi@test.com")
                        .param("phone", "08999912222")
                        .param("address.street", "jalan panjang pendek")
                        .param("address.city", "kebumen")
                        .param("address.country", "indonesia")
                        .param("address.postalCode", "11111")
                        .param("hobbies[0]", "eating")
                        .param("hobbies[1]", "code")
                        .param("hobbies[2]", "sleeping")
                        .param("socialMedia[0].name", "twitter")
                        .param("socialMedia[0].location", "twitter.com/budhi")
                        .param("socialMedia[1].name", "facebook")
                        .param("socialMedia[1].location", "facebook.com/budhi")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person " +
                        "budhi oct octaviansyah with email budhi@test.com and phone 08999912222 " +
                        "with address jalan panjang pendek, kebumen, indonesia, 11111"))
        );

        /**
         * CreatePersonRequest(
         * firstName=budhi, middleName=oct, lastName=octaviansyah, email=budhi@test.com, phone=08999912222,
         * address=CreateAddressRequest(street=jalan panjang pendek, city=kebumen, country=indonesia, postalCode=11111),
         * hobbies=[eating, code, sleeping],
         * socialMedia=[CreateSocialMediaRequest(name=twitter, location=twitter.com/budhi), CreateSocialMediaRequest(name=facebook, location=facebook.com/budhi)])
         */
    }

    @Test
    void testCreatePersonNestedObjectBadRequest() throws Exception {

        // testing dengan parameter object. yang di handle dengan annotation @ModelAttribute
        // @ModelAttribute annotation yang menghendle object/class bean. karna pasti akan melelahkan jika menggunkan @RequestParam
        // @ModelAttribute juga power full dia bisa membaca nedted object yang di sematkan
        // @Valid badrequest dengan attribute yang null

        mockMvc.perform(
                post("/create/personnedtedobject")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                        .param("firstName", "budhi")
                        .param("middleName", "oct")
                        .param("lastName", "octaviansyah")
//                        .param("email", "budhi@test.com")
//                        .param("phone", "08999912222")
                        .param("address.street", "jalan panjang pendek")
                        .param("address.city", "kebumen")
                        .param("address.country", "indonesia")
                        .param("address.postalCode", "11111")
                        .param("hobbies[0]", "eating")
                        .param("hobbies[1]", "code")
                        .param("hobbies[2]", "sleeping")
                        .param("socialMedia[0].name", "twitter")
                        .param("socialMedia[0].location", "twitter.com/budhi")
                        .param("socialMedia[1].name", "facebook")
                        .param("socialMedia[1].location", "facebook.com/budhi")
        ).andExpectAll(
                status().isBadRequest()
        );

        /**
         * CreatePersonRequest(
         * firstName=budhi, middleName=oct, lastName=octaviansyah, email=budhi@test.com, phone=08999912222,
         * address=CreateAddressRequest(street=jalan panjang pendek, city=kebumen, country=indonesia, postalCode=11111),
         * hobbies=[eating, code, sleeping],
         * socialMedia=[CreateSocialMediaRequest(name=twitter, location=twitter.com/budhi), CreateSocialMediaRequest(name=facebook, location=facebook.com/budhi)])
         */
    }

    @Test
    void testCreatePersonBadRequestBindingResult() throws Exception {

        // testing dengan parameter object. yang di handle dengan annotation @ModelAttribute
        // @Valid badrequest dengan attribute yang null

        mockMvc.perform(
                post("/create/person/bindingresult")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//                        .param("firstName", "budhi")
                        .param("middleName", "oct")
                        .param("lastName", "octaviansyah")
//                        .param("email", "budhi@test.com")
//                        .param("phone", "08999912222")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("you send invalid data"))
        );

        /**
         * error messages return BindingResult
         * phone : must not be blank
         * email : must not be blank
         * firstName : must not be blank
         */

    }

}