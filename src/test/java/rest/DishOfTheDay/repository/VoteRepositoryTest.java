package rest.DishOfTheDay.repository;

import com.integralblue.log4jdbc.spring.Log4jdbcAutoConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Poll;
import rest.DishOfTheDay.domain.User;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Profile("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@Import(Log4jdbcAutoConfiguration.class)
public class VoteRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @Autowired
    MenuRepository menuRepository;

    @Test
    @Sql(scripts = {"/TestData/test-users-data.sql",
                    "/TestData/test-restaurants-data.sql",
                    "/TestData/test-menus-data.sql",
                    "/TestData/test-polls-data.sql",
                    "/TestData/test-votes-data.sql"})
    public void findByUserAndPollTest() {
        User user = userRepository.findByName("user3");
        Poll poll = pollRepository.getOne(LocalDate.now());
        Menu menu = menuRepository.getOne(204);
        assertEquals(voteRepository.findByUserAndPoll(user, poll).getId(), Integer.valueOf(303));
    }

    @Test
    @Sql(scripts = {"/TestData/test-users-data.sql",
            "/TestData/test-restaurants-data.sql",
            "/TestData/test-menus-data.sql",
            "/TestData/test-polls-data.sql",
            "/TestData/test-votes-data.sql"})
    public void findByUserAndPollTest2() {
        User user = userRepository.findByName("user3");
        Poll poll = pollRepository.getOne(LocalDate.now());
        Menu menu = menuRepository.getOne(204);
        assertThat(voteRepository.findByUserAndPoll(user, poll).getMenu()).isEqualTo(menu);
    }
}