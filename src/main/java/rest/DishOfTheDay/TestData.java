package rest.DishOfTheDay;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.repository.DishRepository;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Component
@NoArgsConstructor
public class TestData {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public final User ADMIN = new User("admin", passwordEncoder.encode("admin"), "admin1@site.com", Set.of(User.Role.ROLE_ADMIN));
    public final User USER1 = new User("user1", passwordEncoder.encode("user1"), "user1@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER2 = new User("user2", passwordEncoder.encode("user2"), "user2@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER3 = new User("user3", passwordEncoder.encode("user3"), "user3@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER4 = new User("user4", passwordEncoder.encode("user4"), "user4@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER5 = new User("user5", passwordEncoder.encode("user5"), "user5@site.com", Set.of(User.Role.ROLE_USER));

    public final List<User> users = List.of(ADMIN, USER1, USER2, USER3, USER4, USER5);

    public final Restaurant RESTAURANT1 = new Restaurant("Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua");
    public final Restaurant RESTAURANT2 = new Restaurant("Мистер Кэт", "ул.Екатерининская, 11", null);
    public final Restaurant RESTAURANT3 = new Restaurant("Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua");

    public final List<Restaurant> restaurants = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public final Dish DISH1_1 = new Dish("Дичь", 1, RESTAURANT1);
    public final Dish DISH1_2 = new Dish("Компот", 2, RESTAURANT1);
    public final Dish DISH2_1 = new Dish("Рыба заливная", 3, RESTAURANT2);
    public final Dish DISH2_2 = new Dish("Чай зеленый", 4, RESTAURANT2);
    public final Dish DISH3_1 = new Dish("Пицца", 5, RESTAURANT3);
    public final Dish DISH3_2 = new Dish("Кока-Кола", 6, RESTAURANT3);

    public final List<Dish> dishes = List.of(DISH1_1, DISH1_2, DISH2_1, DISH2_2, DISH3_1, DISH3_2);

    public void populate(RestaurantRepository restaurantRepository,
                         UserRepository userRepository,
                         DishRepository dishRepository) {
            userRepository.saveAll(users);
            restaurantRepository.saveAll(restaurants);
            dishRepository.saveAll(dishes);
        }
}
