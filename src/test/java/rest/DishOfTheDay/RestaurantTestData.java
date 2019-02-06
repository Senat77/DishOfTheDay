package rest.DishOfTheDay;

import rest.DishOfTheDay.domain.Restaurant;

import java.util.List;

import static rest.DishOfTheDay.domain.BaseEntity.START_SEQ;

public class RestaurantTestData {

    public static final int RESTAURANT1_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1,"Мистер Кэт", "ул.Екатерининская, 11", null);
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT3, RESTAURANT2);
}
