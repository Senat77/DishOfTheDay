package rest.DishOfTheDay;

import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import rest.DishOfTheDay.repository.*;
import rest.DishOfTheDay.util.AbstractData;

import java.sql.SQLException;
import java.time.Clock;

@SpringBootApplication
public class DishOfTheDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DishOfTheDayApplication.class, args);
	}

    // https://stackoverflow.com/questions/9318116/how-to-run-h2-database-in-server-mode
	// url : jdbc:h2:tcp://localhost:9092/mem:dishoftheday
	@Bean(initMethod = "start", destroyMethod = "stop")
	@Profile("!test")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
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
	@Profile("!test")
	public CommandLineRunner insertData(AbstractData data) {
		return args -> data.populate();
	}
}

