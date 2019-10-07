package rest.DishOfTheDay.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rest.DishOfTheDay.domain.*;
import rest.DishOfTheDay.repository.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Profile({"demo", "dev"})
@Component
public class DemoData implements AbstractData {

    private final UserRepository userRepository;

    private final RestaurantRepository restaurantRepository;

    private final MenuRepository menuRepository;

    private final PollRepository pollRepository;

    private final VoteRepository voteRepository;

    @Autowired
    public DemoData(UserRepository userRepository, RestaurantRepository restaurantRepository, MenuRepository menuRepository, PollRepository pollRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

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

    public final Restaurant RESTAURANT1 = new Restaurant("Restaurant1", "Address1", "restaurant1@site.com");
    public final Restaurant RESTAURANT2 = new Restaurant("Restaurant2", "Address2", null);
    public final Restaurant RESTAURANT3 = new Restaurant("Restaurant3", "Address3", "restaurant3@site.com");

    public final List<Restaurant> restaurants = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    // Menus

    LocalDate date = LocalDate.now();
    public final Menu MENU1_1 = new Menu(RESTAURANT1, List.of(new Dish("Dish1", 1), new Dish("Dish2", 2)));
    public final Menu MENU1_3 = new Menu(date.minusDays(1), RESTAURANT1, List.of(new Dish("Dish3", 3), new Dish("Dish4", 4)));
    public final Menu MENU1_2 = new Menu(date.minusDays(2), RESTAURANT1, List.of(new Dish("Dish5", 5), new Dish("Dish6", 6), new Dish("Dish7", 7)));
    public final Menu MENU2 = new Menu(RESTAURANT2, List.of(new Dish("Dish8", 8), new Dish("Dish9", 9)));
    public final Menu MENU3_1 = new Menu(RESTAURANT3, List.of(new Dish("Dish10", 10), new Dish("Dish11", 11)));
    public final Menu MENU3_2 = new Menu(date.minusDays(1), RESTAURANT3, List.of(new Dish("Dish12", 12), new Dish("Dish13", 13)));

    public final Set<Menu> menus = Set.of(MENU1_1,MENU1_3,MENU1_2, MENU2, MENU3_1, MENU3_2);

    // Polls

    public final Poll POLL1 = new Poll(date, Set.of(MENU1_1,MENU2,MENU3_1));
    public final Poll POLL2 = new Poll(date.minusDays(1), Set.of(MENU1_3, MENU3_2));

    // Votes
    public final Vote VOTE1 = new Vote(POLL1, USER1, MENU1_1);
    public final Vote VOTE2 = new Vote(POLL1, USER2, MENU2);
    public final Vote VOTE3 = new Vote(POLL1, USER3, MENU3_1);
    public final Vote VOTE4 = new Vote(POLL1, USER4, MENU2);
    public final Vote VOTE5 = new Vote(POLL2, USER1, MENU1_3);
    public final Vote VOTE6 = new Vote(POLL2, USER2, MENU3_2);
    public final Vote VOTE7 = new Vote(POLL2, USER3, MENU3_2);
    public final Vote VOTE8 = new Vote(POLL2, USER4, MENU3_2);

    public final List<Vote> votes = List.of(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7, VOTE8);

    public void populate() {
            userRepository.saveAll(users);
            restaurantRepository.saveAll(restaurants);
            menuRepository.saveAll(menus);
            pollRepository.saveAll(Set.of(POLL1, POLL2));
            voteRepository.saveAll(votes);
    }
}
