package com.tutorial.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.mvc.entity.CreateAddressRequest;
import com.tutorial.mvc.entity.CreatePersonRequest;
import com.tutorial.mvc.entity.CreateSocialMediaRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; // class digunakan untuk konversi dari object java ke format json

    @Test
    void testPersonApiValid() throws Exception {

        CreateAddressRequest address = new CreateAddressRequest();
        address.setStreet("jl. panjang pendek");
        address.setCity("kebumen");
        address.setCountry("indonesia");
        address.setPostalCode("11111");

        CreatePersonRequest person = new CreatePersonRequest();
        person.setFirstName("budhi");
        person.setMiddleName("oct");
        person.setLastName("octaviansyah");
        person.setEmail("budhi@test.com");
        person.setPhone("08999912222");
        person.setAddress(address);
        person.setHobbies(List.of("eating", "coding", "sleeping"));
        person.setSocialMedia(new ArrayList<>());
        person.getSocialMedia().add(new CreateSocialMediaRequest("twitter", "twitter.com/budhi"));
        person.getSocialMedia().add(new CreateSocialMediaRequest("facebook", "facebook.com/budhi"));

        String jsonRequest = objectMapper.writeValueAsString(person); // String writeValueAsString(Object value) throws JsonProcessingException // object java konversi ke format JSON

        // test
        // dengan harapan path /api/person
        // content-type yang sudah di set consumes(saat request) dan produce(saat response)
        // content dengan format string json

        // setelah itu berekpetasi dengan status isOk() dan format json(string) sesuai format json harapan
        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isOk(),
                content().json(jsonRequest)
        );

        /**
         * Status Code: 200 OK
         * result responseBody:
         * {"firstName":"budhi,"middleName":"oct","lastName":"octaviansyah","email":budhi@test.com,"phone":08999912222,
         * "address":{"street":"jl. panjang pendek","city":"kebumen","country":"indonesia","postalCode":"11111"},
         * "hobbies":["eating","coding","sleeping"],
         * "socialMedia":[{"name":"twitter","location":"twitter.com/budhi"},{"name":"facebook","location":"facebook.com/budhi"}]}
         */
    }

    @Test
    void testPersonApiValidationBadRequest() throws Exception {

        // di test ini kita tidak akan mengisi attribute yang sudah di set @NotBlank.

        CreateAddressRequest address = new CreateAddressRequest();
        address.setStreet("jl. panjang pendek");
        address.setCity("kebumen");
        address.setCountry("indonesia");
        address.setPostalCode("11111");

        CreatePersonRequest person = new CreatePersonRequest();
        // person.setFirstName("budhi");
        person.setMiddleName("oct");
        person.setLastName("octaviansyah");
        // person.setEmail("budhi@test.com");
        // person.setPhone("08999912222");
        person.setAddress(address);
        person.setHobbies(List.of("eating", "coding", "sleeping"));
        person.setSocialMedia(new ArrayList<>());
        person.getSocialMedia().add(new CreateSocialMediaRequest("twitter", "twitter.com/budhi"));
        person.getSocialMedia().add(new CreateSocialMediaRequest("facebook", "facebook.com/budhi"));

        String jsonRequest = objectMapper.writeValueAsString(person); // String writeValueAsString(Object value) throws JsonProcessingException // object java konversi ke format JSON

        // test
        // dengan harapan path /api/person
        // content-type yang sudah di set consumes(saat request) dan produce(saat response)
        // content dengan format string json

        // setelah itu berekpetasi dengan status isBadRequest() karena attribute yang sudah di validasi @NotBlank tidak di isi. padalah tidak boleh null
        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpectAll(
                status().isBadRequest()
                //content().json(jsonRequest)
        );

        /**
         * Status Code: 400 BadRequest
         * result responseBody:
         * {"firstName":null,"middleName":"oct","lastName":"octaviansyah","email":null,"phone":null,
         * "address":{"street":"jl. panjang pendek","city":"kebumen","country":"indonesia","postalCode":"11111"},
         * "hobbies":["eating","coding","sleeping"],
         * "socialMedia":[{"name":"twitter","location":"twitter.com/budhi"},{"name":"facebook","location":"facebook.com/budhi"}]}
         */

    }


}