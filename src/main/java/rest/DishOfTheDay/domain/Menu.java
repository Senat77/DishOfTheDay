package rest.DishOfTheDay.domain;

import java.time.LocalDate;

public class Menu extends AbstractBaseEntity {

    private LocalDate date;

    private Restaurant restaurant;

    private Dish dish;
}
