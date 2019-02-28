package rest.DishOfTheDay.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import rest.DishOfTheDay.repository.RestaurantRepository;

public abstract class MenuMapperDecorator implements MenuMapper {

    @Autowired
    @Qualifier("delegate")
    private MenuMapper delegate;

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Menu toMenu(MenuReqDTO menuDTO) {
        Menu menu = delegate.toMenu(menuDTO);
        Restaurant restaurant = repository.getOne(menuDTO.getRestaurant_id());
        menu.setRestaurant(restaurant);
        return menu;
    }
}
