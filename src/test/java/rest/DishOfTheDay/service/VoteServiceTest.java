package rest.DishOfTheDay.service;

import org.h2.util.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.PollNotActiveException;
import java.time.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

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
        //System.out.println("===== " + LocalTime.now(clock) + " =====");
        VoteReqDTO req = new VoteReqDTO();
        req.setMenu_id(204);
        service.create(4, req);
        assertEquals(service.getVote(4, LocalDate.now()).getMenu_id(), Integer.valueOf(204));
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}