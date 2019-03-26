package rest.DishOfTheDay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import rest.DishOfTheDay.domain.dto.MenuRespDTO;
import rest.DishOfTheDay.service.MenuService;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {


    static final String REST_URL = "/api/admin/menus";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenuService service;

    @Autowired
    public MenuRestController(MenuService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public MenuRespDTO getMenu(@PathVariable("id") Integer id) {
        log.info("Get menu with id = {}", id);
        return service.get(id);
    }

    @GetMapping("/last_by_restaurant/{restaurant_id}")
    public MenuRespDTO getLastMenu(@PathVariable("restaurant_id") Integer id) {
        log.info("Last menu for Restaurant with id = {}", id);
        return service.getLastByRestaurantId(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create (@Validated(MenuReqDTO.New.class) @RequestBody MenuReqDTO menuDTO) {//(@RequestBody Restaurant restaurant) {
        log.info("Create restaurant {}", menuDTO);
        return new ResponseEntity<> (service.create(menuDTO), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MenuRespDTO update (@RequestBody MenuReqDTO menuDTO, @PathVariable("id") Integer id) {
        return service.update(id, menuDTO);
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }
}
