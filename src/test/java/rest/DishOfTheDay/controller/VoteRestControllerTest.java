package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.DishOfTheDay.domain.dto.PollReqDTO;
import rest.DishOfTheDay.domain.dto.VoteReqDTO;
import rest.DishOfTheDay.service.VoteService;
import rest.DishOfTheDay.util.exception.EntityNotFoundException;
import rest.DishOfTheDay.util.exception.PollNotActiveException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql",
                "/TestData/test-polls-data.sql",
                "/TestData/test-votes-data.sql"})})

public class VoteRestControllerTest extends AbstractControllerTest {

    @Test
    @WithUserDetails("user3")
    public void getMyVote() throws Exception {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        mvc.perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(formattedDate)))
                .andExpect(jsonPath("$.menu_id", is(204)));
    }

    @Test
    @WithUserDetails("user1")
    public void getMyVoteByDate() throws Exception {
        String formattedDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        mvc.perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL + "/getbydate/" + formattedDate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menu_id", is(202)));
    }

    @Test
    @WithUserDetails("user1")
    @ExceptionHandler(EntityNotFoundException.class)
    public void getMyVoteByDate_EntityNotFoundException() throws Exception {
        String formattedDate = LocalDate.now().minusDays(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        mvc.perform(MockMvcRequestBuilders.get(VoteRestController.REST_URL + "/getbydate/" + formattedDate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.Message", is("Entity not found exception")));
    }

    @Test
    @WithUserDetails("user4")
    public void create() throws Exception {
        setTimeForVoteService(LocalTime.of(10,50));
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        VoteReqDTO voteReqDTO = new VoteReqDTO(202, null);
        String inputJson = super.mapToJson(voteReqDTO);
        mvc.perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date", is (formattedDate)))
                .andExpect(jsonPath("$.menu_id", is (202)));
    }

    @Test
    @WithUserDetails("user4")
    @ExceptionHandler(PollNotActiveException.class)
    public void create_PollNotActiveException() throws Exception {
        setTimeForVoteService(LocalTime.of(13,0));
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        VoteReqDTO voteReqDTO = new VoteReqDTO(202, null);
        String inputJson = super.mapToJson(voteReqDTO);
        mvc.perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.Message", is ("Poll exist, but not active")));
    }

    @Test
    @WithUserDetails("user1")
    public void delete() throws Exception {
        setTimeForVoteService(LocalTime.of(10,50));
        mvc.perform(MockMvcRequestBuilders.delete(VoteRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user1")
    public void update() throws Exception {
        setTimeForVoteService(LocalTime.of(10,50));
        VoteReqDTO voteReqDTO = new VoteReqDTO(204, null);
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String inputJson = super.mapToJson(voteReqDTO);
        mvc.perform(MockMvcRequestBuilders.patch(VoteRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is (formattedDate)))
                .andExpect(jsonPath("$.menu_id", is (204)));
    }
}