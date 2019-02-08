package rest.DishOfTheDay;

import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import rest.DishOfTheDay.domain.BaseEntity;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.RestaurantService;

import java.sql.SQLException;
import java.util.List;

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

	/*
	@Bean
	public CommandLineRunner demoData(RestaurantService restaurantService) {
		return args -> {
			restaurantService.saveAll(List.of(
					new Restaurant("Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua"),
					new Restaurant("Мистер Кэт", "ул.Екатерининская, 11", null),
					new Restaurant("Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua")
			));
		};
	}
	*/

	@Bean
	public CommandLineRunner demoData(RestaurantRepository restaurantRepository) {
		return args -> {
			restaurantRepository.saveAll(List.of(
					new Restaurant("Воронцов", "ул.Ришельевская, 55", "vorontsov@od.ua"),
					new Restaurant("Мистер Кэт", "ул.Екатерининская, 11", null),
					new Restaurant("Галактика", "пр.Маршала Жукова, 33", "admin@galactica.od.ua")
			));
		};
	}
}

