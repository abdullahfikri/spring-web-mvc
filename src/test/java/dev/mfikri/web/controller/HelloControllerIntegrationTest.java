package dev.mfikri.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void helloGuest() {
        String response = restTemplate.getForEntity("http://localhost:" + port + "/hello", String.class).getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Guest", response.trim());
    }

    @Test
    void helloFikri() {
        String response = restTemplate.getForEntity("http://localhost:" + port + "/hello?name=Fikri", String.class).getBody();
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Fikri", response.trim());
    }
}
