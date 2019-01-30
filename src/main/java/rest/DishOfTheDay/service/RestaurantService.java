package rest.DishOfTheDay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.util.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public Restaurant get(Integer id) {
        Optional<Restaurant> restaurant = repository.findById(id);
        if(restaurant.isPresent())
            return restaurant.get();
        else
            throw new NotFoundException(Restaurant.class);
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant created = repository.saveAndFlush(restaurant);
        log.debug("[i] Restaurant created : {}", created);
        return created;
    }

    @Transactional
    public Restaurant update(int id, Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Optional<Restaurant> foundOptional = repository.findById(id);
        if(foundOptional.isEmpty())
            throw new NotFoundException(Restaurant.class);
        Restaurant found = foundOptional.get();

        // Dirty code ... Temporary ! :)
        found.setName(restaurant.getName());
        found.setAddress(restaurant.getAddress());
        found.setEmail(restaurant.getEmail());

        repository.saveAndFlush(found);
        log.debug("[i] Restaurant with id={} updated : {}", id, found);

        return found;
    }

    @Transactional
    public void delete (int id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new NotFoundException(Restaurant.class);
    }
}
