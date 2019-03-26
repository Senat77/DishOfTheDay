package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.DishOfTheDay.domain.VotingHistory;

import java.time.LocalDate;

public interface VotingHistoryRepository extends JpaRepository<VotingHistory, LocalDate> {
}
