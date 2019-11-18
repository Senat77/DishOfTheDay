package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql",
                "/TestData/test-polls-data.sql"})})

public class PollRestControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser(roles = "ADMIN")
    public void create() throws Exception {
        PollReqDTO pollReqDTO = new PollReqDTO(LocalDate.now().plusDays(1), Set.of(201, 205));
        String inputJson = super.mapToJson(pollReqDTO);
        mvc.perform(MockMvcRequestBuilders.post(PollRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.menus.length()", is (2)))
                .andExpect(jsonPath("$.menus[0].id", is (201)))
                .andExpect(jsonPath("$.menus[0].restaurant.name", is ("Restaurant1")))
                .andExpect(jsonPath("$.menus[1].id", is (205)))
                .andExpect(jsonPath("$.menus[1].restaurant.address", is ("Address4")));
    }

    @Test
    @WithMockUser(roles = "USER")
    @ExceptionHandler(EntityNotFoundException.class)
    public void get_EntityNotFoundException() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(PollRestController.REST_URL + "/2019-01-01")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("HTTP status\":\"NOT_FOUND"));
        assertTrue(content.contains("Message\":\"Entity not found exception"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void get() throws Exception {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        mvc.perform(MockMvcRequestBuilders.get(PollRestController.REST_URL + "/" + formattedDate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menus.length()", is(3)))
                .andExpect(jsonPath("$.menus[0].restaurant.name", is("Restaurant1")))
                .andExpect(jsonPath("$.menus[1].restaurant.name", is("Restaurant3")))
                .andExpect(jsonPath("$.menus[2].restaurant.name", is("Restaurant4")));
    }
}