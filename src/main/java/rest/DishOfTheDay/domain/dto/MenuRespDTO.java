package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Data
public class MenuRespDTO {

    private Integer id;
    private Restaurant restaurant;
    private LocalDate date;
    private List<Dish> dishes;
}
