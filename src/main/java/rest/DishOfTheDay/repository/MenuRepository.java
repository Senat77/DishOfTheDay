package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest.DishOfTheDay.domain.Menu;

import java.time.LocalDate;
import java.util.Optional;

public interface MenuRepository extends JpaRepository <Menu, Integer> {

    //@Query("select m from Menu m where m.restaurant.id = ?1 and m.date <= current_date order by m.date desc")
    //Optional<Menu> findLastMenuByRestaurantId(Integer id);

    Optional<Menu> findFirstByRestaurantIdAndDateIsLessThanEqualOrderByDateDesc(Integer id, LocalDate date);
}
