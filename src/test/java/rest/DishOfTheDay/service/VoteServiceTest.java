package rest.DishOfTheDay.service;

import mockit.MockUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.domain.dto.VoteRespDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.PollNotActiveException;
import java.time.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

// https://stackoverflow.com/questions/32792000/how-can-i-mock-java-time-localdate-now

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
public class VoteServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void getVote() throws EntityNotFoundException {
        assertEquals(service.getVote(1, LocalDate.now().minusDays(1)).getMenu_id(), Integer.valueOf(202));
    }

    @Test (expected = EntityNotFoundException.class)
    public void getVoteNotFound() throws EntityNotFoundException {
        service.getVote(1, LocalDate.now().minusDays(100));
    }

    @Test
    public void create() throws PollNotActiveException, EntityNotFoundException {
        setTimeForVoteService(LocalTime.of(9, 0, 0));
        VoteReqDTO req = new VoteReqDTO(204, 4);
        service.create(4, req);
        assertEquals(service.getVote(4, LocalDate.now()).getMenu_id(), Integer.valueOf(204));
    }

    @Test (expected = PollNotActiveException.class)
    public void create_PollNotActive () throws PollNotActiveException, EntityNotFoundException {
        setTimeForVoteService(LocalTime.of(11, 0, 1));
        VoteReqDTO req = new VoteReqDTO(204, 4);
        service.create(4, req);
    }

    @Test (expected = EntityNotFoundException.class)
    public void delete() throws EntityNotFoundException, PollNotActiveException {
        setTimeForVoteService(LocalTime.of(9, 0, 0));
        VoteRespDTO resp = service.getVote(1, LocalDate.now());
        assertEquals(resp.getMenu_id(), Integer.valueOf(205));
        service.delete(1);
        resp = service.getVote(1, LocalDate.now());
    }

    @Test
    public void update() throws EntityNotFoundException, PollNotActiveException {
        setTimeForVoteService(LocalTime.of(9, 0, 0));
        VoteRespDTO resp = service.getVote(2, LocalDate.now());
        VoteReqDTO req = new VoteReqDTO(205, 2);
        service.update(2, req);
        assertNotEquals(resp.getMenu_id(), service.getVote(2, LocalDate.now()).getMenu_id());
    }

    public void setTimeForVoteService (LocalTime time) {
        LocalDateTime fixedDateTime = LocalDateTime.of(LocalDate.now(), time);
        Clock fixedClock = Clock.fixed(fixedDateTime.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        service.setClock(fixedClock);
    }
}