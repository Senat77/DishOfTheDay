package rest.DishOfTheDay.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Profile("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    RestaurantRepository repository;

    @Test
    @Sql(scripts = {"/TestData/test-restaurants-data.sql"})
    public void findByNameTest() {
        assertEquals(repository.findByName("Restaurant3").getAddress(), "Address3");
    }

    @Test
    @Sql(scripts = {"/TestData/test-restaurants-data.sql"})
    public void restaurantNotFound() {
        assertNull(repository.findByName("not_existing_restaurant"));
    }
}