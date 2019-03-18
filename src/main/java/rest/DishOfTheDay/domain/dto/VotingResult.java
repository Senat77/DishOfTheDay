package rest.DishOfTheDay.domain.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class VotingResult {
    private LocalDate date;
    List<MenuWithVotes> menuWithVotes = new ArrayList<>();

    public VotingResult(LocalDate date, List<MenuWithVotes> menuWithVotes) {
        this.date = date;
        this.menuWithVotes = menuWithVotes;
    }
}
