package ch.daniil.studyapp.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "review_states")
public class ReviewState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private short box = 1;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate = LocalDate.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Card getCard() { return card; }
    public void setCard(Card card) { this.card = card; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public short getBox() { return box; }
    public void setBox(short box) { this.box = box; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}