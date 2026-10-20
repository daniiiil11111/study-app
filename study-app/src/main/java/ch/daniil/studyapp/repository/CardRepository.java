package ch.daniil.studyapp.repository;

import ch.daniil.studyapp.domain.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCardSetId(Long cardSetId);
}