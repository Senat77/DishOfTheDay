package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import rest.DishOfTheDay.TestDataPopulation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;

@Profile("test")
@DataJpaTest
public class MenuRepositoryTest extends TestDataPopulation {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        restaurantRepository.saveAll(restaurantPopulate());
        menuRepository.saveAll(menuPopulate());
    }

    @Test
    public void getLastMenuByRestaurant() {
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant1).getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void getLastMenuByRestaurant_NotFound() {
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant4));
    }
}
