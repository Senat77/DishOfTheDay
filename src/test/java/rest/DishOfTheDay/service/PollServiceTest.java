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

import java.time.LocalDate;
import java.util.Set;

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

    @Test
    public void create() {
        PollReqDTO pollReqDTO = new PollReqDTO();
        pollReqDTO.setId(LocalDate.now().plusDays(1));
        pollReqDTO.setMenu_id(Set.of(204, 205));
        PollRespDTO pollRespDTO = service.create(pollReqDTO);
    }

    @Test
    public void get() {
    }
}