package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rest.DishOfTheDay.domain.dto.RestaurantRespDTO;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql"})})

public class UserRestControllerTest extends AbstractControllerTest {

    @Test
    public void create() throws Exception {
        UserReqDTO user = new UserReqDTO("NewUser", "newuser@e.mail", "password");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(UserRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isCreated())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        UserRespDTO userRespDTO = super.mapFromJson(content, UserRespDTO.class);
        assertEquals(userRespDTO.getName(), "NewUser");
        assertEquals(userRespDTO.getEmail(), "newuser@e.mail");
    }

    @Test
    public void getAll() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getMe() {
    }

    @Test
    public void editMe() {
    }

    @Test
    public void deleteMe() {
    }
}