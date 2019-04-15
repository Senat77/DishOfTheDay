package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class MenuRepositoryTest {

    @Autowired
    MenuRepository repo;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void getLastMenuByRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant("Name", "Address", "Email@email.com");
        restaurantRepository.save(restaurant);
        LocalDate date = LocalDate.now();
        repo.save(new Menu(date, restaurant, List.of(new Dish("NewDish", 1))));
        repo.save(new Menu(date.minusDays(1), restaurant, List.of(new Dish("NewDish2", 2))));
        assertThat(repo.getLastMenuByRestaurant(restaurant).getDate()).isEqualTo(date);
    }

    @Test
    public void getLastMenuByRestaurant_NotFoundException() {
        Restaurant restaurant = new Restaurant("Name", "Address", "Email@email.com");
        restaurantRepository.save(restaurant);
        repo.getLastMenuByRestaurant(restaurant);
    }
}