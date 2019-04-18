package rest.DishOfTheDay.repository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class VoteRepositoryTest {

    @Autowired
    VoteRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PollRepository pollRepository;

    @BeforeEach
    void setUp() {

    }
}