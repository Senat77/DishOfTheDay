package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    private Restaurant restaurant1, restaurant2;

    @BeforeEach
    public void setUp() {
        LocalDate date = LocalDate.now();
        restaurant1 = new Restaurant("Name1", "Address1", "Email1@email.com");
        restaurant2 = new Restaurant("Name2", "Address2", "Email2@email.com");
        restaurantRepository.saveAll(List.of(restaurant1, restaurant2));
        menuRepository.save(new Menu(date, restaurant1, List.of(new Dish("NewDish", 1))));
        menuRepository.save(new Menu(date.minusDays(1), restaurant1, List.of(new Dish("NewDish2", 2))));
    }

    @Test
    public void getLastMenuByRestaurant() {
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant1).getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void getLastMenuByRestaurant_NotFound() {
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant2));
    }
}