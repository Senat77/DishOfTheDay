package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rest.DishOfTheDay.domain.dto.UserReqDTO;
import rest.DishOfTheDay.domain.dto.UserRespDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    @WithMockUser(roles = "ADMIN")
    public void getAll() throws Exception {
        mvc.perform(get(UserRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(jsonPath("$[0].name", is("admin")))
                .andExpect(jsonPath("$[5].name", is("user5")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(UserRestController.REST_URL + "/3/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user3")
    public void getMe() throws Exception {
        mvc.perform(get(UserRestController.REST_URL + "/profile")
                .secure(true)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("user3")))
                .andExpect(jsonPath("$.email", is("user3@site.com")));
    }

    @Test
    @WithUserDetails("user3")
    public void editMe() throws Exception {
        UserReqDTO userReqDTO = new UserReqDTO("NewUser3", "user3@newmail.ua", "");
        String inputJson = super.mapToJson(userReqDTO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.patch(UserRestController.REST_URL + "/profile")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        UserRespDTO userRespDTO = super.mapFromJson(content, UserRespDTO.class);
        assertEquals(userRespDTO.getName(), "NewUser3");
        assertEquals(userRespDTO.getEmail(), "user3@newmail.ua");
    }

    @Test
    @WithUserDetails("user3")
    public void deleteMe() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(UserRestController.REST_URL + "/profile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}