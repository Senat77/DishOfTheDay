package rest.DishOfTheDay.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.DishOfTheDay.domain.User;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByName() {
        entityManager.persist(new User("new_admin", "password", "e@mai.l", Set.of(User.Role.ROLE_ADMIN)));
        assertThat(userRepository.findByName("new_admin")).isNotEmpty();
    }

    @Test
    public void notFoundByName() {
        assertThat(userRepository.findByName("not_existing_user")).isEmpty();
    }
}