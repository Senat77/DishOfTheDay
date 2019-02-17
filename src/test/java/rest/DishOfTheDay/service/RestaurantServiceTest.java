package rest.DishOfTheDay.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import rest.DishOfTheDay.TestData;
import rest.DishOfTheDay.service.mapper.RestaurantMapper;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ActiveProfiles("test")
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class RestaurantServiceTest {

    @Autowired
    private RestaurantMapper mapper;

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() {
        TestData.populate();
        assertThat(service.getAll()).isEqualTo(mapper.fromRestaurants(TestData.restaurants));
    }

    @Test
    public void get() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}