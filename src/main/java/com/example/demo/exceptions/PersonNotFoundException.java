package com.example.demo.exceptions;

public class PersonNotFoundException extends IllegalArgumentException{
    public PersonNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
