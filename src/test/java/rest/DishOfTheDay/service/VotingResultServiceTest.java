package rest.DishOfTheDay.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;
import rest.DishOfTheDay.domain.dto.MenuWithVotes;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VotingResult;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.PollNotActiveException;

import java.time.*;

import static org.junit.Assert.*;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql",
                "/TestData/test-polls-data.sql",
                "/TestData/test-votes-data.sql"})})
public class VotingResultServiceTest {

    @Autowired
    private VotingResultService service;

    @Autowired
    private VoteService voteService;

    @Test
    public void updateResult() throws PollNotActiveException, EntityNotFoundException {
        setTimeForVoteService(LocalTime.of(9, 0, 0));
        VoteReqDTO voteReqDTO = new VoteReqDTO();
        voteReqDTO.setMenu_id(204);
        voteService.create(4, voteReqDTO);
        VotingResult result = service.getResult(LocalDate.now());
        assertEquals(result.getMenuWithVotes().size(), 3);
        assertEquals(result.getWinner().getMenuRespDTO().getId(), Integer.valueOf(204));
        assertEquals(result.getWinner().getVoteCounter(), Long.valueOf(3));
    }

    @Test
    public void getResult() {
        VotingResult result = service.getResult(LocalDate.now().minusDays(1));
        assertEquals(result.getMenuWithVotes().size(), 2);
        assertEquals(result.getWinner().getMenuRespDTO().getId(), Integer.valueOf(206));
        assertEquals(result.getWinner().getVoteCounter(), Long.valueOf(3));
    }

    public void setTimeForVoteService (LocalTime time) {
        LocalDateTime fixedDateTime = LocalDateTime.of(LocalDate.now(), time);
        Clock fixedClock = Clock.fixed(fixedDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        voteService.setClock(fixedClock);
    }
}