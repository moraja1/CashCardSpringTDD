package cr.ac.una.cashcardspringtdd.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import cr.ac.una.cashcardspringtdd.data.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashCardAPITest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_return_cashcard_when_is_saved_in_database() {

        ResponseEntity<String> response = restTemplate.getForEntity("/api/cashcard/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext docContext = JsonPath.parse(response.getBody());
        Number id = docContext.read("$.id");
        assertThat(id).isNotNull();
    }

    @Test
    void should_return_HttpNotFound_when_cashcard_is_not_saved_in_database() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cashcard/800", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void should_return_HttpCreated_when_cashcard_is_saved_in_database(){
        CashCard cashCard = new CashCard(null, 300.00);
        ResponseEntity<Void> response = restTemplate.postForEntity("/api/cashcard", cashCard, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationHeader = response.getHeaders().getLocation();
        ResponseEntity<String> responseCreated = restTemplate.getForEntity(locationHeader, String.class);
        assertThat(responseCreated.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext docContext = JsonPath.parse(responseCreated.getBody());
        Number amount = docContext.read("$.amount");
        assertThat(amount).isEqualTo(300.0);
    }

    @Test
    void should_return_a_list_of_cashcards_sorted_by_amount_in_a_page_of_five_elements(){

    }
}