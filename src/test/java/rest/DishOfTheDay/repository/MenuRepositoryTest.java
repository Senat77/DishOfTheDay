package rest.DishOfTheDay.repository;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.util.EntityManagerProvider;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import rest.DishOfTheDay.domain.Restaurant;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

//@Profile("test")
@DataJpaTest
public class MenuRepositoryTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.instance("riderDB");

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @DataSet(value = {"restaurants.yml"})
    public void getLastMenuByRestaurant() {
        Restaurant restaurant = restaurantRepository.findByName("Restaurant1");
        System.out.println(restaurant.getAddress());
        assertThat(menuRepository.getLastMenuByRestaurant(restaurant).getDate().isEqual(LocalDate.now()));
    }

    /*@Test
    //public void getLastMenuByRestaurant_NotFound() {
        assertNull(menuRepository.getLastMenuByRestaurant(restaurant4));
    }
     */
}
