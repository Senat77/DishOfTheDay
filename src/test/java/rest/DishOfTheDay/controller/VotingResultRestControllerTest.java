package rest.DishOfTheDay.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import rest.DishOfTheDay.domain.dto.MenuWithVotes;
import rest.DishOfTheDay.domain.dto.VotingResult;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/TestData/test-delete-all.sql",
                "/TestData/test-users-data.sql",
                "/TestData/test-restaurants-data.sql",
                "/TestData/test-menus-data.sql",
                "/TestData/test-polls-data.sql",
                "/TestData/test-votes-data.sql"})})

public class VotingResultRestControllerTest extends AbstractControllerTest {

    @Test
    public void getResult() throws Exception {
        String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(VotingResultRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is(formattedDate)))
                .andExpect(jsonPath("$.menuWithVotes.size()", is(3)))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        VotingResult result = super.mapFromJson(content, VotingResult.class);
        MenuWithVotes menu = result.getWinner();
        assertEquals(menu.getVoteCounter(), Long.valueOf(2));
        assertEquals(menu.getMenuRespDTO().getRestaurant().getName(),"Restaurant3");
    }

    @Test
    public void getResultByDate() {
    }
}