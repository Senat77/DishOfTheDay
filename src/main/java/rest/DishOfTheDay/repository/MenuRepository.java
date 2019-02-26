package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.DishOfTheDay.domain.Menu;

public interface MenuRepository extends JpaRepository <Menu, Integer> {
}
