package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.mapper.RestaurantMapper;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@EnableCaching
public class RestaurantService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    private final RestaurantMapper mapper;

    @Autowired
    public RestaurantService(RestaurantRepository repository, RestaurantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable("restaurants")
    public List<RestaurantRespDTO> getAll() {
        return mapper.fromRestaurants(repository.findAll(new Sort(Sort.Direction.ASC, "name")));
    }

    @Cacheable("restaurants")
    public RestaurantRespDTO get(Integer id) throws EntityNotFoundException {
        return mapper.fromRestaurant(findById(id));
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public RestaurantRespDTO create(RestaurantReqDTO restaurantDTO) {
        Assert.notNull(restaurantDTO, "restaurant must not be null");
        RestaurantRespDTO created = mapper.fromRestaurant(repository.save(mapper.toRestaurant(restaurantDTO)));
        log.info("Restaurant created : {}", created);
        return created;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public RestaurantRespDTO update(RestaurantReqDTO restaurantDTO) throws EntityNotFoundException {
        Assert.notNull(restaurantDTO, "Restaurant must not be null");
        Restaurant restaurant = findById(restaurantDTO.getId());
        mapper.toUpdate(restaurant,restaurantDTO);
        log.debug("[i] Restaurant with id={} updated : {}", restaurant.getId(), restaurant);
        return mapper.fromRestaurant(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    public void delete (int id) throws EntityNotFoundException {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException();
    }

    private Restaurant findById (Integer id) throws EntityNotFoundException {
        Optional<Restaurant> restaurant = repository.findById(id);
        if(restaurant.isPresent())
            return restaurant.get();
        else
            throw new EntityNotFoundException();
    }
}
