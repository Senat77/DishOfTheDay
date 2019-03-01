package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.DishOfTheDay.domain.Poll;

import java.time.LocalDate;

public interface PollRepository extends JpaRepository <Poll, LocalDate> {
}
