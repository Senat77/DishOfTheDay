package rest.DishOfTheDay.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.PollRespDTO;
import rest.DishOfTheDay.repository.MenuRepository;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.IllegalMenuSetOfPollException;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.*;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql",
                "/TestData/test-polls-data.sql"})})
public class PollServiceTest {

    @Autowired
    PollService service;

    @Autowired
    MenuRepository menuRepository;

    @Test
    public void create() throws IllegalMenuSetOfPollException, EntityNotFoundException {
        PollReqDTO pollReqDTO = new PollReqDTO();
        pollReqDTO.setId(LocalDate.now().plusDays(1));
        pollReqDTO.setMenu_id(Set.of(204, 205));
        PollRespDTO pollRespDTO = service.create(pollReqDTO);
        assertEquals(pollRespDTO.getMenus().size(), 2);
        assertNotNull(service.get(LocalDate.now().plusDays(1)));
    }

    @Test (expected = EntityNotFoundException.class)
    public void get() throws EntityNotFoundException {
        PollRespDTO resp = service.get(LocalDate.now().plusDays(5));
    }
}