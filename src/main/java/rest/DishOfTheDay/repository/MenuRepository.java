package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.DishOfTheDay.domain.Menu;

import java.util.Optional;

public interface MenuRepository extends JpaRepository <Menu, Integer> {

    Optional<Menu> findByRestaurantId(Integer id);
}
