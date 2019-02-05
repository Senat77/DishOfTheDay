package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantDTO;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.mapper.RestaurantMapper;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    private final RestaurantMapper mapper;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
        this.mapper = RestaurantMapper.INSTANCE;
    }

    public List<RestaurantDTO> getAll() {
        return mapper.fromRestaurants(repository.findAll(new Sort(Sort.Direction.ASC, "name")));
    }

    public RestaurantDTO get(Integer id) {
        Optional<Restaurant> restaurant = repository.findById(id);
        if(restaurant.isPresent())
            return mapper.fromRestaurant(restaurant.get());
        else
            throw new NotFoundException(Restaurant.class);
    }

    @Transactional
    public RestaurantDTO create(RestaurantDTO restaurantDTO) {
        Assert.notNull(restaurantDTO, "restaurant must not be null");
        RestaurantDTO created = mapper.fromRestaurant(repository.save(mapper.toRestaurant(restaurantDTO)));
        log.info("Restaurant created : {}", created);
        return created;
    }

    @Transactional
    public RestaurantDTO update(int id, RestaurantDTO restaurantDTO) {
        Assert.notNull(restaurantDTO, "restaurant must not be null");
        Optional<Restaurant> foundOptional = repository.findById(id);
        if(foundOptional.isEmpty())
            throw new NotFoundException(Restaurant.class);
        Restaurant found = mapper.toRestaurant(restaurantDTO);
        repository.save(found);
        log.debug("[i] Restaurant with id={} updated : {}", id, found);
        return mapper.fromRestaurant(found);
    }

    @Transactional
    public void delete (int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException(Restaurant.class);
    }
}
