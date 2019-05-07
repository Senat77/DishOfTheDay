package rest.DishOfTheDay.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.Restaurant;

import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

@Profile("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @Sql(scripts = {"/TestData/test-restaurants-data.sql",  "/TestData/test-menus-data.sql"})
    public void getLastMenuByRestaurant() {
        Restaurant restaurant = restaurantRepository.findByName("Restaurant1");
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant).getDate().isEqual(LocalDate.now()));
    }

    @Test
    @Sql(scripts = {"/TestData/test-restaurants-data.sql", "/TestData/test-menus-data.sql"})
    public void getLastMenuByRestaurant_NotFound() {
        Restaurant restaurant = restaurantRepository.findByName("Restaurant2");
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant));
    }
}
