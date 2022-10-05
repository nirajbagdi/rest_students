package com.example.rest_students.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "John Doe",
                    "john.doe@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex.poppins@gmail.com",
                    LocalDate.of(2004, Month.SEPTEMBER, 5)
            );

            studentRepository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }

}
