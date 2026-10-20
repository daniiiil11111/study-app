package ch.daniil.studyapp.repository;

import ch.daniil.studyapp.domain.ReviewState;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewStateRepository extends JpaRepository<ReviewState, Long> {
    List<ReviewState> findByUserIdAndDueDateLessThanEqual(Long userId, LocalDate date);
}