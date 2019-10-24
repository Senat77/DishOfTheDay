package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rest.DishOfTheDay.domain.Dish;
import rest.DishOfTheDay.domain.dto.MenuReqDTO;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql"})})

public class MenuRestControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getMenu() throws Exception {
        mvc.perform(get(MenuRestController.REST_URL + "/204")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurant.name", is("Restaurant3")))
                .andExpect(jsonPath("$.dishes[0].name", is("Dish7")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getLastMenu() throws Exception {
        String dateString = LocalDate.now().toString();
        mvc.perform(get(MenuRestController.REST_URL + "/last_by_restaurant/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurant.name", is("Restaurant1")))
                .andExpect(jsonPath("$.date", is(dateString)));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void create() throws Exception {
        MenuReqDTO menuReqDTO = new MenuReqDTO(102, LocalDate.now(), List.of(new Dish("NewDish", 333)));
        String inputJson = super.mapToJson(menuReqDTO);
        mvc.perform(MockMvcRequestBuilders.post(MenuRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.restaurant.name", is("Restaurant2")))
                .andExpect(jsonPath("$.dishes[0].name", is("NewDish")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void update() throws Exception {
        MenuReqDTO menuReqDTO = new MenuReqDTO(101, LocalDate.now(), List.of(new Dish("NewDish", 333)));
        String inputJson = super.mapToJson(menuReqDTO);
        mvc.perform(MockMvcRequestBuilders.patch(MenuRestController.REST_URL + "/201")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurant.name", is("Restaurant1")))
                .andExpect(jsonPath("$.dishes[0].name", is("NewDish")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(MenuRestController.REST_URL + "/201")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}