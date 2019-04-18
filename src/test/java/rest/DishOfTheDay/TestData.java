package rest.DishOfTheDay;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Menu;
import rest.DishOfTheDay.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class TestData {

    public static Restaurant RESTAURANT1 = new Restaurant("Restaurant1", "Address1", "restaurant1@site.com");
    public static Restaurant RESTAURANT2 = new Restaurant("Restaurant2", "Address2", null);
    public static Restaurant RESTAURANT3 = new Restaurant("Restaurant3", "Address3", "restaurant3@site.com");

    public static LocalDate date = LocalDate.now();
    public static Menu MENU1_1 = new Menu(RESTAURANT1, List.of(new Dish("Dish1", 1), new Dish("Dish2", 2)));
    public static Menu MENU1_3 = new Menu(date.minusDays(1), RESTAURANT1, List.of(new Dish("Dish3", 3), new Dish("Dish4", 4)));
    public static Menu MENU1_2 = new Menu(date.minusDays(2), RESTAURANT1, List.of(new Dish("Dish5", 5), new Dish("Dish6", 6), new Dish("Dish7", 7)));
    public static Menu MENU2 = new Menu(RESTAURANT2, List.of(new Dish("Dish8", 8), new Dish("Dish9", 9)));
    public static Menu MENU3 = new Menu(RESTAURANT3, List.of(new Dish("Dish10", 10), new Dish("Dish11", 11)));
}
