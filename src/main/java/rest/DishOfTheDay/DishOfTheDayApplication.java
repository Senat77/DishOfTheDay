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
import rest.DishOfTheDay.repository.*;

import java.sql.SQLException;

@SpringBootApplication
public class DishOfTheDayApplication {

	public static void main(String[] args) {

		SpringApplication.run(DishOfTheDayApplication.class, args);
	}

    // https://stackoverflow.com/questions/9318116/how-to-run-h2-database-in-server-mode
	// url : jdbc:h2:tcp://localhost:9092/mem:dishoftheday
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
	public CommandLineRunner demoData(TestData testData,
									  UserRepository userRepository,
									  RestaurantRepository restaurantRepository,
									  MenuRepository menuRepository,
									  PollRepository pollRepository,
									  VoteRepository voteRepository) {
		return args -> testData.populate(restaurantRepository, userRepository, menuRepository, pollRepository, voteRepository);
	}
}

