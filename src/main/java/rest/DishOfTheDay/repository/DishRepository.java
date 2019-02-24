package rest.DishOfTheDay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.DishOfTheDay.domain.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
}