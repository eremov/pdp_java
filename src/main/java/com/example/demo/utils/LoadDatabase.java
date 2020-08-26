package com.example.demo.utils;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person("Ivan", "Ivanov", 22, true)));
            log.info("Preloading " + repository.save(new Person("Yauheni", "Ktotov", 32, false)));
            log.info("Preloading " + repository.save(new Person("Katrin", "Someone", 12, true)));
            log.info("Preloading " + repository.save(new Person("Rita", "Incognito", 51, false)));
            log.info("Preloading " + repository.save(new Person("Nikita", "Soldier", 34, true)));
        };
    }
}