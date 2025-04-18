package dev.mfikri.web.controller;

import dev.mfikri.web.service.HelloService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HelloService helloService;

    @BeforeEach
    void setUp() {
        Mockito.when(helloService.hello(Mockito.anyString()))
                .thenReturn("Hello Guys");
    }

    @Test
    void helloName() throws Exception {
        mockMvc.perform(get("/hello").queryParam("name", "Fikri"))
                .andExpectAll(
                        status().isOk(),
                        content().string(Matchers.containsString("Hello Guys"))
                );
    }


}