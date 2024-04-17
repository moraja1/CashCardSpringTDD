package cr.ac.una.cashcardspringtdd.data.repositories;

import cr.ac.una.cashcardspringtdd.data.CashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashCardRepository extends CrudRepository<CashCard, Long> {
}
