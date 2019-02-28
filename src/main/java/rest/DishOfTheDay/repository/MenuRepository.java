package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rest.DishOfTheDay.domain.Menu;

import java.time.LocalDate;
import java.util.Optional;

public interface MenuRepository extends JpaRepository <Menu, Integer> {

    @Query("select m from Menu m where m.restaurant.id = ?1 and m.date = (select max(date) from Menu where date <= ?2)")
    Optional<Menu> findLastMenuByRestaurantId(Integer id, LocalDate date);

    // Optional<Menu> findFirstByRestaurantIdAndDateIsLessThanEqualOrderByDateDesc(Integer id, LocalDate date);
}
