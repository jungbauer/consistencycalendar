package com.jungbauer.consistencycalendar.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "completions")
@Entity
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    public Completion() {}

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
