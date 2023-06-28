package com.tutorial.mvc.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {

    /**
     * Konversi Tipe Data
     * ● Kita tahu bahwa query parameter itu datanya adalah String
     * ● Namun jika kita membutuhkan datanya dalam bentuk tipe data lain, Spring bisa secara otomatis
     *   melakukan konversi tipe datanya menggunakan fitur Converter yang pernah kita bahas di materi
     *   Spring Config Properties
     *
     *
     * object utils interface Converter<S, T>
     * T convert(S source);
     */

    // SimpleDateFormat adalah object converter dari Date string ke format Date native
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // set bentuk format string date 2023-06-22

    @Override
    public Date convert(String source) {

        try{

            return dateFormat.parse(source); // Date parse(String source) // akan konversi dari string ke format Date

        }catch (ParseException e){
            log.warn("Error convert data from string {}", source, e);
            return null;
        }
    }

}
