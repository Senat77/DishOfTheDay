package rest.DishOfTheDay.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import rest.DishOfTheDay.domain.Restaurant;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;
import rest.DishOfTheDay.repository.RestaurantRepository;
import rest.DishOfTheDay.service.mapper.RestaurantMapper;
import rest.DishOfTheDay.service.mapper.RestaurantMapperImpl;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@Profile("test")
@RunWith(SpringRunner.class)
public class RestaurantServiceTest {

    @TestConfiguration
    static class RestaurantServiceTestContextConfiguration {

        @Bean
        public RestaurantService restaurantService() {
            return new RestaurantService();
        }

        @Bean
        public RestaurantMapper restaurantMapper() {
            return new RestaurantMapperImpl();
        }
    }

    @Autowired
    private RestaurantService service;

    @Autowired
    private RestaurantMapper mapper;

    @MockBean
    private RestaurantRepository repository;

    @Before
    public void setUp() {
        Restaurant restaurant1 = new Restaurant(0, "Restaurant1","Address1", "restaurant1@site.com");
        Restaurant restaurant2 = new Restaurant(0, "Restaurant2","Address2", "restaurant2@site.com");
        Mockito.when(repository.findByName(restaurant1.getName())).thenReturn(restaurant1);
        Mockito.when(repository.findByName(restaurant2.getName())).thenReturn(restaurant2);
        Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.of(restaurant2));
        Mockito.when(repository.findAll(new Sort(Sort.Direction.ASC, "name"))).thenReturn(List.of(restaurant1, restaurant2));
    }

    @Test
    public void getAll() {
        List<RestaurantRespDTO> list = service.getAll();
        assertThat(list).hasSize(2);
        assertThat(list.get(0).getAddress()).isEqualTo("Address1");
    }

    @Test
    public void get() throws EntityNotFoundException {
        RestaurantRespDTO restaurantRespDTO = service.get(1);
        assertThat(restaurantRespDTO.getEmail()).isEqualTo("restaurant2@site.com");
    }

    @Test
    public void create() {
        RestaurantReqDTO restaurant3 = new RestaurantReqDTO ("Restaurant3", "Address3", "Restaurant3@site.com");
        RestaurantRespDTO restaurantRespDTO = service.create(restaurant3);
        assertThat(restaurantRespDTO.getName()).isEqualTo(restaurant3.getName());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}