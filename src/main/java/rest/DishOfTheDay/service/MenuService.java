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
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.time.LocalDate;
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
    public MenuRespDTO get(Integer id) throws EntityNotFoundException {
        return mapper.fromMenu(getById(id));
    }

    @Cacheable("menus")
    public MenuRespDTO getLastByRestaurantId(Integer id) throws EntityNotFoundException {
        Optional<Restaurant> oRestaurant = restaurantRepository.findById(id);
        Restaurant restaurant;
        if(oRestaurant.isPresent()) {
            restaurant = oRestaurant.get();
        }
        else
            throw new EntityNotFoundException();
        Optional<Menu> oMenu = repository.findFirstByRestaurant_IdOrderByDateDesc(id);
        if(oMenu.isPresent()) {
            return mapper.fromMenu(oMenu.get());
        }
        else
            throw new EntityNotFoundException();
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

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    public MenuRespDTO update(int id, MenuReqDTO menuDTO) throws EntityNotFoundException {
        Assert.notNull(menuDTO, "Menu must not be null");
        Menu menu = getById(id);
        mapper.toUpdate(menu, menuDTO);
        log.debug("[i] Menu with id={} updated : {}", id, menu);
        return mapper.fromMenu(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    public void delete(int id) throws EntityNotFoundException {
        repository.delete(getById(id));
    }

    private Menu getById(Integer id) throws EntityNotFoundException {
        Optional<Menu> oMenu = repository.findById(id);
        if(oMenu.isPresent())
            return oMenu.get();
        else
            throw new EntityNotFoundException();
    }
}
