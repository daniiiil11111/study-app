package ch.daniil.studyapp.repository;

import ch.daniil.studyapp.domain.CardSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSetRepository extends JpaRepository<CardSet, Long> {
    List<CardSet> findByProjectId(Long projectId);
}