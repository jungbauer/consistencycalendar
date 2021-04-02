package com.jungbauer.consistencycalendar.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompletionRepository extends JpaRepository<Completion, Integer> {

    List<Completion> findByHabit(Habit habit);

    List<Completion> findByHabitAndDate(Habit habit, LocalDate date);

    boolean existsByHabitAndDate(Habit habit, LocalDate date);
}
