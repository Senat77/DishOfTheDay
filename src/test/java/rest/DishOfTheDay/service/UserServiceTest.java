package rest.DishOfTheDay.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
           scripts = {"/TestData/test-delete-all.sql",
                      "/TestData/test-users-data.sql"})})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    CacheManager cacheManager;

    @Before
    public void beforeTest() {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void getAll() {
        List<UserRespDTO> users = userService.getAll();
        assertThat(users).hasSize(6);
        assertEquals(users.get(0).getName(), "admin");
    }

    @Test
    public void get() {
        assertThat(userService.get(3).getName()).isEqualTo("user3");
    }

    @Test
    public void create() {
        UserReqDTO userDTO = new UserReqDTO("newuser", "newuser@site.com", "newpassword");
        int id = userService.create(userDTO).getId();
        assertEquals(userService.get(id).getEmail(), "newuser@site.com");
    }

    @Test
    public void update() {
        UserReqDTO userDTO = new UserReqDTO("admin", "newmail@site.com", "newpassword");
        UserRespDTO updateUser = userService.update(0, userDTO);
        assertEquals(userService.get(updateUser.getId()).getEmail(), "newmail@site.com");
    }

    @Test
    public void delete() throws EntityNotFoundException {
        UserRespDTO userDTO = userService.get(0);
        userService.delete(0);
        assertThat(userService.getAll()).doesNotContain(userDTO);
    }
}