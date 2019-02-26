package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    RestaurantRespDTO fromRestaurant (Restaurant restaurant);

    Restaurant toRestaurant (RestaurantReqDTO restaurantDTO);

    List<RestaurantRespDTO> fromRestaurants (List<Restaurant> restaurants);

    List<Restaurant> toRestaurants (List<RestaurantReqDTO> restaurantDTOS);
}
