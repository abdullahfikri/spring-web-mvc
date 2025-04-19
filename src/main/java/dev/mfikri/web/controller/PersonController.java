package dev.mfikri.web.controller;

import dev.mfikri.web.model.CreatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

    @PostMapping(value = "/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String createPerson(@ModelAttribute @Valid CreatePersonRequest request) {
        System.out.println(request.getHobbies());
        System.out.println(request.getSocialMedias());

        return "Success create person " +
                request.getFirstName() + " " +
                request.getMiddleName() + " " +
                request.getLastName() + " " +
                "with email " + request.getEmail() + ", " +
                "with phone " + request.getPhone() + ", " +
                "and with address " +
                request.getAddress().getStreet() + ", " +
                request.getAddress().getCity() + ", " +
                request.getAddress().getCountry() + ", " +
                request.getAddress().getPostalCode() + ", ";
    }
}
