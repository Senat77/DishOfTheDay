package rest.DishOfTheDay.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.time.LocalDate;

@Profile("test")
@DataJpaTest
public class MenuRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @DataSet({"restaurants.yml"})
    public void getLastMenuByRestaurant() {
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant1).getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void getLastMenuByRestaurant_NotFound() {
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant4));
    }
}
