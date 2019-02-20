package rest.DishOfTheDay;

import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest.DishOfTheDay.domain.User;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DishOfTheDayApplication {

	public static void main(String[] args) {

		SpringApplication.run(DishOfTheDayApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public TestData testData() {
		return new TestData();
	}

	@Bean
	public CommandLineRunner demoData(UserRepository userRepository, RestaurantRepository restaurantRepository) {
		/*
		return args -> 	userRepository.saveAll(List.of(
				new User("admin", passwordEncoder().encode("admin"), "admin1@site.com", Set.of(User.Role.ROLE_ADMIN, User.Role.ROLE_USER)),
				new User("user", passwordEncoder().encode("user"), "user@site.com", Set.of(User.Role.ROLE_USER))));
		*/
		return args -> testData().populate(restaurantRepository, userRepository);
	}
}

