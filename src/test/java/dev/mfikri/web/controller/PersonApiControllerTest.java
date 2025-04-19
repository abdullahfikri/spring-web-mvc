package dev.mfikri.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.mfikri.web.model.CreateAddressRequest;
import dev.mfikri.web.model.CreatePersonRequest;
import dev.mfikri.web.model.CreateSocialMediaRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createPerson() throws Exception {
        CreatePersonRequest request = getCreatePersonRequest();
        String requestJson = objectMapper.writeValueAsString(request);
        
        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isCreated(),
                content().string(Matchers.containsString(requestJson))
        );
    }

    @Test
    void createPersonValidationError() throws Exception {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setLastName("Fikri");
        request.setEmail("fikri@example.com");
        request.setPhone("080000001");
        request.setAddress(new CreateAddressRequest("Jalan Setia Budi", "Jakarta", "Indonesia", "000001"));
        request.setHobbies(List.of("Coding", "Reading Book"));

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(
                post("/api/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("Validation error :"))
        );
    }

    private static CreatePersonRequest getCreatePersonRequest() {
        CreatePersonRequest request = new CreatePersonRequest();
        request.setFirstName("Muhammad");
        request.setLastName("Fikri");
        request.setEmail("fikri@example.com");
        request.setPhone("080000001");
        request.setAddress(new CreateAddressRequest("Jalan Setia Budi", "Jakarta", "Indonesia", "000001"));
        request.setHobbies(List.of("Coding", "Reading Book"));
        request.setSocialMedias(List.of(new CreateSocialMediaRequest("Instagram", "instagram.com/fikriexample"),
                new CreateSocialMediaRequest("LinkedIn", "linkedin.com/fikriexample")));
        return request;
    }
}