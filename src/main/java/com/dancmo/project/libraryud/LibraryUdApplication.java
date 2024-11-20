package com.dancmo.project.libraryud;

import com.dancmo.project.libraryud.persistence.entity.Cliente;
import com.dancmo.project.libraryud.persistence.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LibraryUdApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryUdApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ClienteRepository clienteRepository) {
       return args -> {
        };
    }

}
