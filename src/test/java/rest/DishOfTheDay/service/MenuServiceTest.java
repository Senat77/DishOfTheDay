package rest.DishOfTheDay.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import java.time.LocalDate;
import static org.junit.Assert.*;

@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                    "/TestData/test-restaurants-data.sql",
                    "/TestData/test-menus-data.sql"})})

public class MenuServiceTest {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    MenuService service;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
        cacheManager.getCache("menus").clear();
    }

    @Test
    public void getLastByRestaurantId() throws EntityNotFoundException {
        MenuRespDTO menu = service.getLastByRestaurantId(101);
        assertEquals(menu.getDate(), LocalDate.now());
        assertEquals(menu.getRestaurant().getName(), "Restaurant1");
    }

    @Test (expected = EntityNotFoundException.class)
    public void menuNotFound() throws EntityNotFoundException {
        MenuRespDTO menu = service.getLastByRestaurantId(102);
    }
}