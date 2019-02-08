package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.DishOfTheDay.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
