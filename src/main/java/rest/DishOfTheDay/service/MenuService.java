package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;
import rest.DishOfTheDay.repository.MenuRepository;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.mapper.MenuMapper;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@EnableCaching
public class MenuService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuRepository repository;

    private final RestaurantRepository restaurantRepository;

    private final MenuMapper mapper;

    @Autowired
    public MenuService(RestaurantRepository restaurantRepository, MenuRepository repository, RestaurantRepository restaurantRepository1, MenuMapper mapper) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository1;
        this.mapper = mapper;
    }

    @Cacheable("menus")
    public List<MenuRespDTO> getAll() {
        return mapper.fromMenus(repository.findAll());
    }

    @Cacheable("menus")
    public MenuRespDTO get(Integer id) {
        Optional<Menu> menu = repository.findById(id);
        if(menu.isPresent()) {
            return mapper.fromMenu(menu.get());
        }
        else
            throw new NotFoundException(Menu.class);
    }

    public MenuRespDTO getLastByRestaurantId(Integer id) {
        Optional<Restaurant> oResataurant = restaurantRepository.findById(id);
        Restaurant restaurant = null;
        if(oResataurant.isPresent()) {
            restaurant = oResataurant.get();
        }
        else
            throw new NotFoundException(Restaurant.class);
        Optional<Menu> oMenu = repository.findFirstByRestaurantIdAndDateIsLessThanEqualOrderByDateDesc(restaurant.getId(), LocalDate.now());
        if(oMenu.isPresent()) {
            return mapper.fromMenu(oMenu.get());
        }
        else
            throw new NotFoundException(Menu.class);
    }
}
