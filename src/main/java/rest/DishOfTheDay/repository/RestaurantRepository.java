package rest.DishOfTheDay.repository;

import rest.DishOfTheDay.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Object findByName(String name);

    Object findByNameOrderByName(String name);
}
