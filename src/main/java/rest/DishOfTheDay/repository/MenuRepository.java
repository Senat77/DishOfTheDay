package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;

public interface MenuRepository extends JpaRepository <Menu, Integer> {

    @Query("select m from Menu m where m.restaurant = ?1 and m.date = (select max(date) from Menu m where m.restaurant = ?1)")
    Menu getLastMenuByRestaurant(Restaurant r);
}
