package rest.DishOfTheDay.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.domain.dto.UserRespDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Sql(scripts = {"/TestData/test-users-data.sql"})
    public void getAll() {
        List<UserRespDTO> users = userService.getAll();
        assertThat(users).hasSize(6);
        assertEquals(users.get(0).getName(), "admin");
    }

    @Test
    public void get() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}