package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
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
    public MenuRespDTO get(Integer id) {
        Optional<Menu> menu = repository.findById(id);
        if(menu.isPresent()) {
            return mapper.fromMenu(menu.get());
        }
        else
            throw new NotFoundException(Menu.class);
    }

    @Cacheable("menus")
    public MenuRespDTO getLastByRestaurantId(Integer id) {
        Optional<Restaurant> oRestaurant = restaurantRepository.findById(id);
        Restaurant restaurant;
        if(oRestaurant.isPresent()) {
            restaurant = oRestaurant.get();
        }
        else
            throw new NotFoundException(Restaurant.class);
        Optional<Menu> oMenu = repository.findLastMenuByRestaurantId(restaurant.getId(), LocalDate.now());
        if(oMenu.isPresent()) {
            return mapper.fromMenu(oMenu.get());
        }
        else
            throw new NotFoundException(Menu.class);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    public MenuRespDTO create(MenuReqDTO menuDTO) {
        Assert.notNull(menuDTO, "Menu must not be null");
        Menu menu = mapper.toMenu(menuDTO);
        repository.save(menu);
        log.info("Menu created : {}", menu);
        return mapper.fromMenu(menu);
    }
}
