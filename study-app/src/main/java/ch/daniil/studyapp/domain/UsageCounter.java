package ch.daniil.studyapp.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usage_counters")
public class UsageCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDate day;

    @Column(nullable = false)
    private int count = 0;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDate getDay() { return day; }
    public void setDay(LocalDate day) { this.day = day; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
}