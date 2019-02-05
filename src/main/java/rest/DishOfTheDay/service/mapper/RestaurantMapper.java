package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantDTO;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    RestaurantDTO fromRestaurant (Restaurant restaurant);

    Restaurant toRestaurant (RestaurantDTO restaurantDTO);

    List<RestaurantDTO> fromRestaurants (List<Restaurant> restaurants);

    List<Restaurant> toRestaurants (List<RestaurantDTO> restaurantDTOS);
}
