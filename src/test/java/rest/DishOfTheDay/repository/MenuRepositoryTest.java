package rest.DishOfTheDay.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
//@DataJpaTest
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
}