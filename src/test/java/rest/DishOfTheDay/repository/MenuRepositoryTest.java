package rest.DishOfTheDay.repository;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.DishOfTheDayApplication;
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
    @Sql(scripts = {"/datasets/test-restaurants-data.sql"})
    public void getLastMenuByRestaurant() {
        Restaurant restaurant = restaurantRepository.findByName("Restaurant1");
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant).getDate().isEqual(LocalDate.now()));
    }

    @Test
    @Sql(scripts = {"/datasets/test-restaurants-data.sql"})
    public void getLastMenuByRestaurant_NotFound() {
        Restaurant restaurant = restaurantRepository.findByName("Restaurant4");
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant));
    }
}
