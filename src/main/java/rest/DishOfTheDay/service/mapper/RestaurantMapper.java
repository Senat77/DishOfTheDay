package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        nullValueCheckStrategy = ALWAYS,
        nullValueMappingStrategy = RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = IGNORE
)
public interface RestaurantMapper {

    RestaurantRespDTO fromRestaurant (Restaurant restaurant);

    Restaurant toRestaurant (RestaurantReqDTO restaurantDTO);

    Restaurant toUpdate(@MappingTarget Restaurant restaurant, RestaurantReqDTO restaurantDTO);

    List<RestaurantRespDTO> fromRestaurants (List<Restaurant> restaurants);

    List<Restaurant> toRestaurants (List<RestaurantReqDTO> restaurantDTOS);
}
