package ch.daniil.studyapp.repository;

import ch.daniil.studyapp.domain.UsageCounter;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageCounterRepository extends JpaRepository<UsageCounter, Long> {
    Optional<UsageCounter> findByUserIdAndDay(Long userId, LocalDate day);
}