package rest.DishOfTheDay;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Component
public class TestData {

    public static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static User ADMIN = new User("admin", passwordEncoder.encode("admin"), "admin1@site.com", Set.of(User.Role.ROLE_ADMIN));
    public static User USER1 = new User("user1", passwordEncoder.encode("user1"), "user1@site.com", Set.of(User.Role.ROLE_USER));
    public static User USER2 = new User("user2", passwordEncoder.encode("user2"), "user2@site.com", Set.of(User.Role.ROLE_USER));
    public static User USER3 = new User("user3", passwordEncoder.encode("user3"), "user3@site.com", Set.of(User.Role.ROLE_USER));
    public static User USER4 = new User("user4", passwordEncoder.encode("user4"), "user4@site.com", Set.of(User.Role.ROLE_USER));
    public static User USER5 = new User("user5", passwordEncoder.encode("user5"), "user5@site.com", Set.of(User.Role.ROLE_USER));

    public static List<User> users = List.of(ADMIN, USER1, USER2, USER3, USER4, USER5);

    public static Restaurant RESTAURANT1 = new Restaurant("Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua");
    public static Restaurant RESTAURANT2 = new Restaurant("Мистер Кэт", "ул.Екатерининская, 11", null);
    public static Restaurant RESTAURANT3 = new Restaurant("Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua");

    public static List<Restaurant> restaurants = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public void populate(RestaurantRepository restaurantRepository, UserRepository userRepository) {
            userRepository.saveAll(users);
            restaurantRepository.saveAll(restaurants);
        }
}
