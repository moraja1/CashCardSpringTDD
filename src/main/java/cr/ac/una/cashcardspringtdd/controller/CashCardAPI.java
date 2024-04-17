package cr.ac.una.cashcardspringtdd.controller;

import cr.ac.una.cashcardspringtdd.data.CashCard;
import cr.ac.una.cashcardspringtdd.data.repositories.CashCardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CashCardAPI {
    private final CashCardRepository cashCardRepository;

    public CashCardAPI(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/cashcard/{id}")
    private ResponseEntity<CashCard> getCashCard(@PathVariable Long id) {
        Optional<CashCard> cashCard = cashCardRepository.findById(id);

        return cashCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/cashcard")
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard cashCard) {
        CashCard savedCashCard = cashCardRepository.save(cashCard);
        URI location = UriComponentsBuilder.fromPath("/api/cashcard/{id}").buildAndExpand(savedCashCard.id()).toUri();
        return ResponseEntity.created(location).build();
    }
}
