package rest.DishOfTheDay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rest.DishOfTheDay.domain.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query (value = "select r from Restaurant r order by name")
    List<Restaurant> getAllOrderByName();

    @Query (value = "select r from Restaurant r where r.id = ?1")
    Restaurant get(Integer id);
}
