package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static rest.DishOfTheDay.TestData.*;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class MenuRepositoryTest {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        restaurantRepository.saveAll(List.of(RESTAURANT1, RESTAURANT2));
        menuRepository.saveAll(List.of(MENU1_1));
    }

    @Test
    public void getLastMenuByRestaurant() {
        assertThat(menuRepository.getLastMenuByRestaurant(RESTAURANT1).getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void getLastMenuByRestaurant_NotFound() {
        assertNull(menuRepository.getLastMenuByRestaurant(RESTAURANT2));
    }
}
