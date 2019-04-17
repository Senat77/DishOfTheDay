package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import rest.DishOfTheDay.domain.User;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Set;

@DataJpaTest
@ActiveProfiles("dev")
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    private User user, admin;

    @BeforeEach
    void setUp() {
        admin = new User("admin", "admin", "admin@mail.com", Set.of(User.Role.ROLE_ADMIN));
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