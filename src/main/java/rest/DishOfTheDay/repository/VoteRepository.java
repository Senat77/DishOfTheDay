package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.DishOfTheDay.domain.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository <Vote, Integer> {

    Optional<Vote> findByUserIdAndPollId(Integer userId, LocalDate date);

    List<Vote> findByPollId(LocalDate date);
}
