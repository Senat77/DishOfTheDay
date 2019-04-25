package rest.DishOfTheDay.repository;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import rest.DishOfTheDay.domain.User;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    private User user, admin;

    @BeforeClass
    void setUp() {
        admin = new User("admin", "admin", "admin@mail.com", Set.of(User.Role.ROLE_ADMIN, User.Role.ROLE_USER));
        user = new User("user", "user", "user@mail.com", Set.of(User.Role.ROLE_USER));
        repository.saveAll(List.of(admin, user));
    }

    @Test
    public void findByNameWithRoles() {
        assertEquals(repository.findByName("admin").getRoles(), Set.of(User.Role.ROLE_ADMIN));
    }

    @Test
    public void userNotFound() {
        assertEquals(repository.findByName("not_existing_user"), null);
    }
}