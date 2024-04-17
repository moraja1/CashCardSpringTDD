package cr.ac.una.cashcardspringtdd.data;


import org.springframework.data.annotation.Id;

public record CashCard(@Id Long id, Double amount) {
}
