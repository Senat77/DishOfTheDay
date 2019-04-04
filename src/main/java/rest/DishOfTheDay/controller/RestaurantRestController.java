package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;
import rest.DishOfTheDay.service.RestaurantService;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {

    static final String REST_URL = "/api/admin/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final RestaurantService service;

    @Autowired
    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<RestaurantRespDTO> getAll() {
        log.info("get all restaurants");
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public RestaurantRespDTO getRestaurant(@PathVariable("id") Integer id) throws EntityNotFoundException {
        log.info("get restaurant with id = {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create (@Validated(RestaurantReqDTO.New.class) @RequestBody RestaurantReqDTO restaurantDTO) {//(@RequestBody Restaurant restaurant) {
        log.info("Create restaurant {}", restaurantDTO);
        return new ResponseEntity<> (service.create(restaurantDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable ("id") Integer id) throws EntityNotFoundException {
        log.info("Delete Restaurant id = {}", id);
        service.delete(id);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestaurantRespDTO update (@RequestBody RestaurantReqDTO restaurantDTO,
                                     @PathVariable("id") Integer id) throws EntityNotFoundException {
        restaurantDTO.setId(id);
        return service.update(restaurantDTO);
    }
}
