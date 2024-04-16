package cr.ac.una.cashcardspringtdd.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CashCardAPI {

    @GetMapping("/cashcard")
    private ResponseEntity<String> getCashCard() {
        return ResponseEntity.ok("{}");
    }
}
