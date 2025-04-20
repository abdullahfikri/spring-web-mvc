package dev.mfikri.web.controller;

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
    private MockMvc mockMvc;


    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("firstName", "Muhammad")
                        .param("middleName", "Abu")
                        .param("lastName", "Fikri")
                        .param("email", "fikri@example.com")
                        .param("phone", "0800000001")
                        .param("address.street", "Jalan Setia Budi")
                        .param("address.city", "Makasar")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "000000")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/WiduriFikri")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Muhammad Abu Fikri " +
                        "with email fikri@example.com, with phone 0800000001, " +
                        "and with address Jalan Setia Budi, Makasar, Indonesia, 000000"))
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("middleName", "Abu")
                        .param("lastName", "Fikri")
                        .param("email", "fikri@example.com")
                        .param("address.street", "Jalan Setia Budi")
                        .param("address.city", "Makasar")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "000000")
                        .param("hobbies[0]", "Coding")
                        .param("hobbies[1]", "Reading")
                        .param("socialMedias[0].name", "Facebook")
                        .param("socialMedias[0].location", "facebook.com/WiduriFikri")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }
}