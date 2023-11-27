package com.example.securityprojectapp.util;


import com.example.securityprojectapp.models.Person;
import com.example.securityprojectapp.services.PeopleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleServices peopleServices;

    @Autowired
    public PersonValidator(PeopleServices peopleServices) {
        this.peopleServices = peopleServices;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (peopleServices.findByUsername((Person) target).isEmpty()){
            peopleServices.registrationUser((Person) target);
        } else {
            errors.rejectValue("username","", "Человек с таким именем уже существует");
        }
    }
}
