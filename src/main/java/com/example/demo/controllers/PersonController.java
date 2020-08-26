package com.example.demo.controllers;

import com.example.demo.exceptions.PersonNotFoundException;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.utils.TrackTime;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/")
class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @TrackTime
    @GetMapping("/persons")
    public List<Person> getAll() {
        return repository.findAll();
    }

    @TrackTime
    @PostMapping("persons")
    public Person createPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @TrackTime
    @GetMapping("persons/{id}")
    public Person findById(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("persons/{id}")
    public Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return repository.findById(id)
            .map(person -> {
                person.setFirstName(newPerson.getFirstName());
                person.setLastName(newPerson.getLastName());
                person.setMarried(newPerson.getMarried());
                person.setAge(newPerson.getAge());
                return repository.save(person);
            })
            .orElseGet(() -> {
                newPerson.setId(id);
                return repository.save(newPerson);
            });
    }

    @DeleteMapping("persons/{id}")
    public void deletePerson(@PathVariable Long id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PersonNotFoundException(id);
        }
    }
}
