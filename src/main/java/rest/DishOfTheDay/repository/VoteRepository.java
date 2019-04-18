package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.Vote;
import java.util.List;

public interface VoteRepository extends JpaRepository <Vote, Integer> {

    @Query("select v from Vote v where v.user = ?1 and v.poll = ?2")
    Vote findByUserAndPoll(User user, Poll poll);

    @Query("select v from Vote v where v.poll = ?1")
    List<Vote> findByPoll(Poll poll);
}
