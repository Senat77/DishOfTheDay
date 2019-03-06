package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest.DishOfTheDay.domain.Vote;

import java.time.LocalDate;
import java.util.Optional;

public interface VoteRepository extends JpaRepository <Vote, Integer> {

    // @Query("select v from Vote v where v.user.id = ?1 and v.poll.id = ?2")
    //@Query("select v from Vote v where v.user.id = ?1 and v.poll.id = ?2")
    Optional<Vote> findByUserIdAndPollId(Integer userId, LocalDate date);
}
