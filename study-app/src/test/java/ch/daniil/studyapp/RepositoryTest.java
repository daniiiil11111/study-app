package ch.daniil.studyapp;

import ch.daniil.studyapp.domain.*;
import ch.daniil.studyapp.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class RepositoryTest {

    @Autowired UserRepository users;
    @Autowired ProjectRepository projects;
    @Autowired CardSetRepository sets;
    @Autowired CardRepository cards;
    @Autowired EntityManager em;

    @Test
    void saveAndLoadProjectWithSetsAndCards() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPasswordHash("not-a-real-hash");
        users.save(user);

        Project project = new Project();
        project.setOwner(user);
        project.setName("German A2");
        project.setType("GERMAN");
        projects.save(project);

        CardSet set = new CardSet();
        set.setProject(project);
        set.setName("Unit 1");
        sets.save(set);

        Card card = new Card();
        card.setCardSet(set);
        card.setType("VOCAB");
        card.setFront("der Tisch");
        card.setBack("table");
        cards.save(card);

        em.flush();
        em.clear();

        Card loaded = cards.findById(card.getId()).orElseThrow();
        assertEquals("table", loaded.getBack());
        assertEquals("Unit 1", loaded.getCardSet().getName());
        assertEquals(1, projects.findByOwnerId(user.getId()).size());
        assertEquals(1, cards.findByCardSetId(set.getId()).size());
    }
}