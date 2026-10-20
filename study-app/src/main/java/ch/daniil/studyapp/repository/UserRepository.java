package ch.daniil.studyapp.repository;

import ch.daniil.studyapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}