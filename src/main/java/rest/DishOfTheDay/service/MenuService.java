package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;
import rest.DishOfTheDay.repository.MenuRepository;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.mapper.MenuMapper;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@EnableCaching
public class MenuService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuRepository repository;

    private final MenuMapper mapper;

    @Autowired
    public MenuService(RestaurantRepository restaurantRepository, MenuRepository repository, MenuMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable("menus")
    public MenuRespDTO get(Integer id) {
        Optional<Menu> menu = repository.findById(id);
        if(menu.isPresent()) {
            MenuRespDTO dto = mapper.fromMenu(menu.get());
            return dto;
        }
        else
            throw new NotFoundException(Menu.class);
    }
}
