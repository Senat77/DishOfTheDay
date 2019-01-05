package com.nodomain.DishOfTheDay.repository;

import com.nodomain.DishOfTheDay.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    Object findByName(String name);
}
