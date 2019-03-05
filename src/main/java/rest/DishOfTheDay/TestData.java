package rest.DishOfTheDay;

import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.*;
import rest.DishOfTheDay.repository.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@NoArgsConstructor
public class TestData {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    // Users

    public final User ADMIN = new User("admin", passwordEncoder.encode("admin"), "admin1@site.com", Set.of(User.Role.ROLE_ADMIN));
    public final User USER1 = new User("user1", passwordEncoder.encode("user1"), "user1@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER2 = new User("user2", passwordEncoder.encode("user2"), "user2@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER3 = new User("user3", passwordEncoder.encode("user3"), "user3@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER4 = new User("user4", passwordEncoder.encode("user4"), "user4@site.com", Set.of(User.Role.ROLE_USER));
    public final User USER5 = new User("user5", passwordEncoder.encode("user5"), "user5@site.com", Set.of(User.Role.ROLE_USER));

    public final List<User> users = List.of(ADMIN, USER1, USER2, USER3, USER4, USER5);

    // Restaurants

    public final Restaurant RESTAURANT1 = new Restaurant("Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua");
    public final Restaurant RESTAURANT2 = new Restaurant("Мистер Кэт", "ул.Екатерининская, 11", null);
    public final Restaurant RESTAURANT3 = new Restaurant("Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua");

    public final List<Restaurant> restaurants = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    // Menus

    LocalDate date = LocalDate.now();
    public final Menu MENU1_1 = new Menu(RESTAURANT1, List.of(new Dish("Первое", 1), new Dish("Второе", 2)));
    public final Menu MENU1_3 = new Menu(date.minusDays(1), RESTAURANT1, List.of(new Dish("Беляш", 11), new Dish("Кофе", 21)));
    public final Menu MENU1_2 = new Menu(date.minusDays(2), RESTAURANT1, List.of(new Dish("Гуляш", 1), new Dish("Чай", 2), new Dish("Салат Весенний", 10)));
    public final Menu MENU2 = new Menu(RESTAURANT2, List.of(new Dish("Борщ", 3), new Dish("Котлета", 4)));
    public final Menu MENU3 = new Menu(RESTAURANT3, List.of(new Dish("Суши", 5), new Dish("Чай", 6)));

    public final Set<Menu> menus = Set.of(MENU1_2, MENU2, MENU3);

    // Polls

    public final Poll POLL = new Poll(date, menus);

    // Votes
    public final Vote VOTE1 = new Vote(POLL, USER1, MENU1_2);
    public final Vote VOTE2 = new Vote(POLL, USER2, MENU2);
    public final Vote VOTE3 = new Vote(POLL, USER3, MENU3);
    public final Vote VOTE4 = new Vote(POLL, USER4, MENU2);
    public final Vote VOTE5 = new Vote(POLL, USER5, MENU2);

    public final List<Vote> votes = List.of(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5);

    public void populate(RestaurantRepository restaurantRepository,
                         UserRepository userRepository,
                         MenuRepository menuRepository,
                         PollRepository pollRepository,
                         VoteRepository voteRepository) {
            userRepository.saveAll(users);
            restaurantRepository.saveAll(restaurants);
            menuRepository.saveAll(menus);
            pollRepository.saveAll(Set.of(POLL));
            voteRepository.saveAll(votes);
        }
}
