package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.DishOfTheDay.domain.dto.RestaurantReqDTO;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql"})})

public class RestaurantRestControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAll() throws Exception {
        mvc.perform(get(RestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("Restaurant1")))
                .andExpect(jsonPath("$[3].name", is("Restaurant4")));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getAllForbidden() throws Exception {
        mvc.perform(get(RestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllNotAuthorized() throws Exception {
        mvc.perform(get(RestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void getRestaurant() throws Exception {
        mvc.perform(get(RestaurantRestController.REST_URL + "/103")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Restaurant3")))
                .andExpect(jsonPath("$.address", is("Address3")))
                .andExpect(jsonPath("$.email", is("restaurant3@site.com")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void create() throws Exception {
        RestaurantReqDTO restaurantReqDTO = new RestaurantReqDTO("Restaurant5", "Address5", null);
        String inputJson = super.mapToJson(restaurantReqDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(RestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        RestaurantRespDTO restaurantRespDTO = super.mapFromJson(content, RestaurantRespDTO.class);
        assertEquals(restaurantRespDTO.getName(), "Restaurant5");
        assertEquals(restaurantRespDTO.getAddress(), "Address5");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(RestaurantRestController.REST_URL + "/103")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    @ExceptionHandler(EntityNotFoundException.class)
    public void delete_EntityNotFoundException() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(RestaurantRestController.REST_URL + "/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("HTTP status\":\"NOT_FOUND"));
        assertTrue(content.contains("Message\":\"Entity not found exception"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void update() throws Exception {
        RestaurantReqDTO restaurantReqDTO = new RestaurantReqDTO("NewName", "NewAddress", "");
        String inputJson = super.mapToJson(restaurantReqDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(RestaurantRestController.REST_URL + "/101")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        RestaurantRespDTO restaurantRespDTO = super.mapFromJson(content, RestaurantRespDTO.class);
        assertEquals(restaurantRespDTO.getName(), "NewName");
        assertEquals(restaurantRespDTO.getAddress(), "NewAddress");
    }
}