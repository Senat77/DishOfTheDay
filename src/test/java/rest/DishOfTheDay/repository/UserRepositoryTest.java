package rest.DishOfTheDay.repository;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.User;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Profile("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    @Sql(scripts = {"/TestData/test-users-data.sql"})
    public void findByNameWithRoles() {
        assertEquals(repository.findByName("admin").getRoles(), Set.of(User.Role.ROLE_ADMIN));
    }

    @Test
    @Sql(scripts = {"/TestData/test-users-data.sql"})
    public void userNotFound() {
        assertNull(repository.findByName("not_existing_user"));
    }
}