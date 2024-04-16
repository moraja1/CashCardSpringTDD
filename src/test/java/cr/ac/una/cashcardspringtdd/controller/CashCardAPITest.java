package cr.ac.una.cashcardspringtdd.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardAPITest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_return_cashcard_when_is_saved_in_database() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cashcard", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}