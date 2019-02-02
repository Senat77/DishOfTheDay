package rest.DishOfTheDay.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantDTO;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    //@Mapping(source = "name", target = "name")
    RestaurantDTO fromRestaurant (Restaurant restaurant);

    //@Mapping(source = "name", target = "name")
    Restaurant toRestaurant (RestaurantDTO restaurantDTO);
}
